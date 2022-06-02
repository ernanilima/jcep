package br.com.ernanilima.jcep.service;

import br.com.ernanilima.jcep.domain.ViaCep;

public interface AddressService {
    ViaCep findByZipCode(String zipCode);
}
