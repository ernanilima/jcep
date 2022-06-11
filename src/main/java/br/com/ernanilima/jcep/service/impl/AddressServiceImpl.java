package br.com.ernanilima.jcep.service.impl;

import br.com.ernanilima.jcep.domain.*;
import br.com.ernanilima.jcep.dto.AddressDto;
import br.com.ernanilima.jcep.dto.ViaCepDto;
import br.com.ernanilima.jcep.repository.*;
import br.com.ernanilima.jcep.service.AddressService;
import br.com.ernanilima.jcep.service.async.AddressAsync;
import br.com.ernanilima.jcep.service.exception.ZipCodeNoFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.text.MessageFormat;
import java.util.Optional;

import static br.com.ernanilima.jcep.utils.I18n.NOT_FOUND_ZIP_CODE;
import static br.com.ernanilima.jcep.utils.I18n.getMessage;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private WebClient webClient;
    @Autowired
    private AddressAsync addressAsync;

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
        AddressDto addressDto;
        boolean isError = (viaCep == null || viaCep.getErro() != null && viaCep.getErro());

        if (!isError) {
            addressDto = toAddress(viaCep);
        } else {
            throw new ZipCodeNoFoundException(MessageFormat.format(getMessage(NOT_FOUND_ZIP_CODE), zipCode.toString()));
        }

        return addressDto;
    }

    private AddressDto toAddress(ViaCepDto viaCep) {
        State state = stateRepository.findByAcronym(viaCep.getUf());
        Address address = viaCep.toAddress(state);
        addressAsync.asyncSaveAddress(address);
        return new AddressDto(address);
    }
}
