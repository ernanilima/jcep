package br.com.ernanilima.jcep.service.impl;

import br.com.ernanilima.jcep.common.ComboBox;
import br.com.ernanilima.jcep.domain.Country;
import br.com.ernanilima.jcep.dto.CountryDto;
import br.com.ernanilima.jcep.repository.CountryRepository;
import br.com.ernanilima.jcep.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Page<ComboBox> findAllCountry(Pageable pageable) {
        Page<Country> countries = countryRepository.findAll(pageable);
        return CountryDto.getComboBox(countries);
    }
}
