package br.com.ernanilima.jcep.service.impl;

import br.com.ernanilima.jcep.common.ComboBox;
import br.com.ernanilima.jcep.domain.City;
import br.com.ernanilima.jcep.dto.CityDto;
import br.com.ernanilima.jcep.param.ParamCountryAndRegionOrState;
import br.com.ernanilima.jcep.repository.CityRepository;
import br.com.ernanilima.jcep.service.CityService;
import br.com.ernanilima.jcep.service.exception.CityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.ernanilima.jcep.utils.I18n.NOT_FOUND_CITY_BY_COUNTRY_OR_REGION_OR_STATE;
import static br.com.ernanilima.jcep.utils.I18n.getMessage;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public Page<ComboBox> findAllCityByCountryOrCountryAndRegionOrCountryAndState(ParamCountryAndRegionOrState param, Pageable pageable) {
        Page<City> cities = cityRepository.findAllByCountry_AcronymAndRegion_NameOrState_Acronym(
                param.getPais(), param.getRegiao(), param.getEstado(), pageable);

        return Optional.ofNullable(CityDto.getComboBox(cities))
                .orElseThrow(() -> new CityNotFoundException(getMessage(NOT_FOUND_CITY_BY_COUNTRY_OR_REGION_OR_STATE)));
    }
}
