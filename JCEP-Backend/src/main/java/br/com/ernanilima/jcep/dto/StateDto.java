package br.com.ernanilima.jcep.dto;

import br.com.ernanilima.jcep.common.ComboBox;
import br.com.ernanilima.jcep.domain.State;
import br.com.ernanilima.jcep.utils.Utils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.ernanilima.jcep.utils.Utils.getValueEnumType;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StateDto implements Serializable {

    public static Page<ComboBox> getComboBox(Page<State> states) {
        if (states.isEmpty()) return null;
        List<ComboBox> comboBox = states.stream().map(state ->
                        ComboBox.builder()
                                .id(state.getIdState())
                                .value(getValueEnumType(state.getName()))
                                .description(state.getName())
                                .acronym(state.getAcronym())
                                .code(Utils.toString(state.getCode()))
                                .build())
                .collect(Collectors.toList());
        return new PageImpl<>(comboBox, states.getPageable(), states.getTotalElements());
    }
}
