package br.com.ernanilima.jcep.service.async;

import br.com.ernanilima.jcep.domain.Address;
import br.com.ernanilima.jcep.domain.City;
import br.com.ernanilima.jcep.repository.AddressRepository;
import br.com.ernanilima.jcep.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AddressAsync {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CityRepository cityRepository;

    @Async
    public void asyncSaveAddress(Address address) {
        City city = cityRepository.save(address.getCity());
        address.setCity(city);
        addressRepository.save(address);
    }
}
