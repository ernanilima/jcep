package br.com.ernanilima.jcep.service;

import br.com.ernanilima.jcep.dto.CountryDto;

import java.util.List;

public interface CountryService {
    List<CountryDto.ComboBox> findAllCountry();
}
