package br.com.ernanilima.jcep.service.impl;

import br.com.ernanilima.jcep.domain.Country;
import br.com.ernanilima.jcep.dto.CountryDto;
import br.com.ernanilima.jcep.repository.CountryRepository;
import br.com.ernanilima.jcep.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public List<CountryDto.ComboBox> findAllCountry() {
        List<Country> countries = countryRepository.findAll();
        return CountryDto.getComboBox(countries);
    }
}
