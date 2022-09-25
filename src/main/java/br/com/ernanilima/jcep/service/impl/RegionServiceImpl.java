package br.com.ernanilima.jcep.service.impl;

import br.com.ernanilima.jcep.common.ComboBox;
import br.com.ernanilima.jcep.domain.Region;
import br.com.ernanilima.jcep.dto.RegionDto;
import br.com.ernanilima.jcep.repository.RegionRepository;
import br.com.ernanilima.jcep.service.RegionService;
import br.com.ernanilima.jcep.service.exception.RegionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

import static br.com.ernanilima.jcep.utils.I18n.*;

@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionRepository regionRepository;

    @Override
    public List<ComboBox> findAllRegionByCountry(String acronym) {
        List<Region> regions = regionRepository.findByCountry_Acronym(acronym);
        return Optional.ofNullable(RegionDto.getComboBox(regions))
                .orElseThrow(() -> new RegionNotFoundException(
                        MessageFormat.format(getMessage(NOT_FOUND_REGION_BY_COUNTRY), acronym)));
    }
}
