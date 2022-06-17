package br.com.ernanilima.jcep.utils;

import br.com.ernanilima.jcep.domain.Country;
import br.com.ernanilima.jcep.domain.Region;
import br.com.ernanilima.jcep.domain.State;
import br.com.ernanilima.jcep.domain.br.Estado;
import br.com.ernanilima.jcep.domain.br.Pais;
import br.com.ernanilima.jcep.domain.br.Regiao;
import br.com.ernanilima.jcep.repository.CountryRepository;
import br.com.ernanilima.jcep.repository.RegionRepository;
import br.com.ernanilima.jcep.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataBase {

    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private StateRepository stateRepository;

    public void createDataDatabase() {
        Country country = Pais.getCountry();
        if (isNotCountry())
            country = countryRepository.save(country);

        List<Region> regions = Regiao.getRegions(country);
        if (isNotRegion())
            regions = regionRepository.saveAll(regions);

        List<State> states = Estado.getStates(country, regions);
        if (isNotState())
            stateRepository.saveAll(states);
    }

    private boolean isNotCountry() {
        List <Country> countries =  countryRepository.findAll();
        return countries.size() == 0;
    }

    private boolean isNotRegion() {
        List<Region> regions = regionRepository.findAll();
        return regions.size() == 0;
    }

    private boolean isNotState() {
        List<State> states = stateRepository.findAll();
        return states.size() == 0;
    }
}
