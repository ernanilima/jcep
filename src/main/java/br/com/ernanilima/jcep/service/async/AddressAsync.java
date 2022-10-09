package br.com.ernanilima.jcep.service.async;

import br.com.ernanilima.jcep.domain.Address;
import br.com.ernanilima.jcep.domain.City;
import br.com.ernanilima.jcep.repository.AddressRepository;
import br.com.ernanilima.jcep.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class AddressAsync {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CityRepository cityRepository;

    @Async
    public void asyncSaveAddress(Address address) {
        Optional<City> cityOpt = cityRepository.findByCode(address.getCode())
                .map(city -> {
                    if (Objects.isNull(city.getAreaCode())) {
                        city.setAreaCode(address.getCity().getAreaCode());
                        cityRepository.save(city);
                    }
                    return city;
                });

        cityOpt.ifPresent(city -> {
            address.setCity(city);
            addressRepository.save(address);
        });
    }
}
