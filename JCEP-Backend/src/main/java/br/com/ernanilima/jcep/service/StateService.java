package br.com.ernanilima.jcep.service;

import br.com.ernanilima.jcep.common.ComboBox;
import br.com.ernanilima.jcep.param.ParamCountryAndRegion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StateService {

    Page<ComboBox> findAllStateByCountryOrCountryAndRegion(ParamCountryAndRegion param, Pageable pageable);
}
