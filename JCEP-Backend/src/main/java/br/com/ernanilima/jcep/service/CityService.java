package br.com.ernanilima.jcep.service;

import br.com.ernanilima.jcep.common.ComboBox;
import br.com.ernanilima.jcep.param.ParamCountryAndRegionOrState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CityService {

    Page<ComboBox> findAllCityByCountryOrCountryAndRegionOrCountryAndState(ParamCountryAndRegionOrState param, Pageable pageable);
}
