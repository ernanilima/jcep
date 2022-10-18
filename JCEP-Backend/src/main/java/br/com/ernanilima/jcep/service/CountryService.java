package br.com.ernanilima.jcep.service;

import br.com.ernanilima.jcep.common.ComboBox;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CountryService {
    Page<ComboBox> findAllCountry(Pageable pageable);
}
