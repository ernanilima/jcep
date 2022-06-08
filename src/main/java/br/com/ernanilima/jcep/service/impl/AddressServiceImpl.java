package br.com.ernanilima.jcep.service.impl;

import br.com.ernanilima.jcep.domain.*;
import br.com.ernanilima.jcep.dto.AddressDto;
import br.com.ernanilima.jcep.dto.ViaCepDto;
import br.com.ernanilima.jcep.repository.*;
import br.com.ernanilima.jcep.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

import static br.com.ernanilima.jcep.utils.Utils.toInteger;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private WebClient webClient;

    @Override
    public AddressDto findByZipCode(Integer zipCode) {
        // buscar no banco de dados
        Optional<Address> address = addressRepository.findByZipCode(zipCode);
        return address.map(AddressDto::new).orElseGet(() ->
                // buscar na API do ViaCep
                findByZipCodeViaCep(zipCode));
    }

    private AddressDto findByZipCodeViaCep(Integer zipCode) {
        // busca o endereco com base no CEP
        ViaCepDto viaCep = webClient.method(HttpMethod.GET).uri("{zipCode}/json", zipCode).retrieve().bodyToMono(ViaCepDto.class).block();
        AddressDto addressDto = new AddressDto();
        addressDto.setError(viaCep == null || viaCep.getErro() != null && viaCep.getErro());

        // com endereco
        if (!addressDto.isError()) {
            addressDto = toAddress(viaCep);
        }

        return addressDto;
    }

    private AddressDto toAddress(ViaCepDto viaCep) {
        State state = stateRepository.findByAcronym(viaCep.getUf());
        Address address = Address.builder()
                .zipCode(toInteger(viaCep.getCep()))
                .country(state.getCountry())
                .region(state.getRegion())
                .state(state)
                .city(
                        City.builder()
                                .name(viaCep.getLocalidade())
                                .code(toInteger(viaCep.getIbge()))
                                .country(state.getCountry())
                                .region(state.getRegion())
                                .state(state)
                                .build()
                )
                .street(viaCep.getLogradouro())
                .complement(viaCep.getComplemento())
                .code(toInteger(viaCep.getIbge()))
                .areaCode(toInteger(viaCep.getDdd()))
                .build();

        return new AddressDto(address);
    }
}
