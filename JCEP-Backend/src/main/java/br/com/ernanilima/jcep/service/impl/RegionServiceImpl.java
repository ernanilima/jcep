package br.com.ernanilima.jcep.service.impl;

import br.com.ernanilima.jcep.common.ComboBox;
import br.com.ernanilima.jcep.domain.Region;
import br.com.ernanilima.jcep.dto.RegionDto;
import br.com.ernanilima.jcep.repository.RegionRepository;
import br.com.ernanilima.jcep.service.RegionService;
import br.com.ernanilima.jcep.service.exception.RegionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;

import static br.com.ernanilima.jcep.utils.I18n.NOT_FOUND_REGION_BY_COUNTRY;
import static br.com.ernanilima.jcep.utils.I18n.getMessage;

@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionRepository regionRepository;

    @Override
    public Page<ComboBox> findAllRegionByCountry(String acronym, Pageable pageable) {
        Page<Region> regions = regionRepository.findByCountry_Acronym(acronym, pageable);
        return Optional.ofNullable(RegionDto.getComboBox(regions))
                .orElseThrow(() -> new RegionNotFoundException(
                        MessageFormat.format(getMessage(NOT_FOUND_REGION_BY_COUNTRY), acronym)));
    }
}
