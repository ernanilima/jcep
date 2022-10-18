package br.com.ernanilima.jcep.service;

import br.com.ernanilima.jcep.dto.AddressDto;

public interface AddressService {
    AddressDto findByZipCode(String zipCode);
}
