package br.com.ernanilima.jcep.service;

import br.com.ernanilima.jcep.common.ComboBox;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;

public interface RegionService {
    Page<ComboBox> findAllRegionByCountry(@NotNull String acronym, Pageable pageable);
}
