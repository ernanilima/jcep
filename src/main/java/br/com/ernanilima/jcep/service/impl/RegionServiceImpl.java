package br.com.ernanilima.jcep.service.impl;

import br.com.ernanilima.jcep.common.ComboBox;
import br.com.ernanilima.jcep.domain.Region;
import br.com.ernanilima.jcep.dto.RegionDto;
import br.com.ernanilima.jcep.repository.RegionRepository;
import br.com.ernanilima.jcep.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionRepository regionRepository;

    @Override
    public List<ComboBox> findAllRegionByCountry(String acronym) {
        Optional<List<Region>> regions = regionRepository.findByCountry_Acronym(acronym);
        return regions.map(RegionDto::getComboBox).orElse(null);
    }
}
