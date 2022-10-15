package br.com.ernanilima.jcep.service.impl;

import br.com.ernanilima.jcep.common.ComboBox;
import br.com.ernanilima.jcep.domain.State;
import br.com.ernanilima.jcep.dto.CountryOrRegionDto;
import br.com.ernanilima.jcep.dto.StateDto;
import br.com.ernanilima.jcep.repository.StateRepository;
import br.com.ernanilima.jcep.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StateServiceImpl implements StateService {

    @Autowired
    private StateRepository stateRepository;

    @Override
    public Page<ComboBox> findAllStateByCountryOrRegion(CountryOrRegionDto param, Pageable pageable) {
        Page<State> states = stateRepository.findAllByCountry_AcronymOrRegion_NameIgnoreCase(param.getPais(), param.getRegiao(), pageable);

        return Optional.ofNullable(StateDto.getComboBox(states)).get();
    }
}
