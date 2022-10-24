package br.com.ernanilima.jcep.service.impl;

import br.com.ernanilima.jcep.common.ComboBox;
import br.com.ernanilima.jcep.domain.State;
import br.com.ernanilima.jcep.param.ParamCountryAndRegion;
import br.com.ernanilima.jcep.dto.StateDto;
import br.com.ernanilima.jcep.repository.StateRepository;
import br.com.ernanilima.jcep.service.StateService;
import br.com.ernanilima.jcep.service.exception.StateNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.ernanilima.jcep.utils.I18n.NOT_FOUND_STATE_BY_COUNTRY_OR_REGION;
import static br.com.ernanilima.jcep.utils.I18n.getMessage;

@Service
public class StateServiceImpl implements StateService {

    @Autowired
    private StateRepository stateRepository;

    @Override
    public Page<ComboBox> findAllStateByCountryOrCountryAndRegion(ParamCountryAndRegion param, Pageable pageable) {
        Page<State> states = stateRepository.findAllByCountry_AcronymOrCountry_AcronymAndRegion_NameIgnoreCase(
                param.getPais(), param.getRegiao(), pageable);

        return Optional.ofNullable(StateDto.getComboBox(states))
                .orElseThrow(() -> new StateNotFoundException(getMessage(NOT_FOUND_STATE_BY_COUNTRY_OR_REGION)));
    }
}
