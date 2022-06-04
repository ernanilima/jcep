package br.com.ernanilima.jcep.service.impl;

import br.com.ernanilima.jcep.domain.Address;
import br.com.ernanilima.jcep.domain.ViaCep;
import br.com.ernanilima.jcep.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import static br.com.ernanilima.jcep.utils.Utils.toInteger;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private WebClient webClient;

    @Override
    public Address findByZipCode(String zipCode) {
        ViaCep viaCep = webClient.method(HttpMethod.GET).uri("{zipCode}/json", zipCode).retrieve().bodyToMono(ViaCep.class).block();
        Address address = viaCep == null ? null : new Address(viaCep);

        if (address != null && viaCep.getErro() == null) {
            // endereco encontrado na API do ViaCep
            address.setZipCode(toInteger(zipCode));
            return address;
        }
        address = new Address();
        return address;
    }
}
