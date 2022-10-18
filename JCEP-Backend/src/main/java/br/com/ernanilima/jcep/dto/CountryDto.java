package br.com.ernanilima.jcep.dto;

import br.com.ernanilima.jcep.common.ComboBox;
import br.com.ernanilima.jcep.domain.Country;
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
public class CountryDto implements Serializable {

    public static Page<ComboBox> getComboBox(Page<Country> countries) {
        List<ComboBox> comboBox = countries.stream().map(country ->
                        ComboBox.builder()
                                .id(country.getIdCountry())
                                .value(getValueEnumType(country.getName()))
                                .description(country.getName())
                                .acronym(country.getAcronym())
                                .code(Utils.toString(country.getCode()))
                                .build())
                .collect(Collectors.toList());
        return new PageImpl<>(comboBox);
    }
}
