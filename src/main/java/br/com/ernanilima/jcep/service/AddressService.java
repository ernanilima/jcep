package br.com.ernanilima.jcep.service;

import br.com.ernanilima.jcep.domain.Address;

public interface AddressService {
    Address findByZipCode(String zipCode);
}
