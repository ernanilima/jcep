package br.com.ernanilima.jcep.service;

import br.com.ernanilima.jcep.common.ComboBox;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface RegionService {
    List<ComboBox> findAllRegionByCountry(@NotNull String acronym);
}
