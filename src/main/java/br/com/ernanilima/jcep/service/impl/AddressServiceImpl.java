package br.com.ernanilima.jcep.service.impl;

import br.com.ernanilima.jcep.domain.ViaCep;
import br.com.ernanilima.jcep.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private WebClient webClient;

    @Override
    public ViaCep findByZipCode(String zipCode) {
        Mono<ViaCep> mono = webClient.method(HttpMethod.GET).uri("{zipCode}/json", zipCode).retrieve().bodyToMono(ViaCep.class);
        ViaCep viaCep = mono.block();
        if (viaCep != null && viaCep.getErro() == null) {
            // endereco encontrado na API do ViaCep
            viaCep.setCep(zipCode);
            return viaCep;
        }
        viaCep = new ViaCep();
        viaCep.setErro(true);
        return viaCep;
    }
}
