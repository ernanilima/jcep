package br.com.ernanilima.jcep.dto;

import br.com.ernanilima.jcep.common.ComboBox;
import br.com.ernanilima.jcep.domain.City;
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
public class CityDto implements Serializable {

    public static Page<ComboBox> getComboBox(Page<City> cities) {
        if (cities.isEmpty()) return null;
        List<ComboBox> comboBox = cities.stream().map(state ->
                        ComboBox.builder()
                                .id(state.getIdCity())
                                .value(getValueEnumType(state.getName()))
                                .description(state.getName())
                                .code(Utils.toString(state.getCode()))
                                .build())
                .collect(Collectors.toList());
        return new PageImpl<>(comboBox);
    }
}
