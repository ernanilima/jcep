package br.com.ernanilima.jcep.service;

import br.com.ernanilima.jcep.common.ComboBox;
import br.com.ernanilima.jcep.dto.CountryOrRegionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StateService {

    Page<ComboBox> findAllStateByCountryOrRegion(CountryOrRegionDto param, Pageable pageable);
}
