package br.com.ernanilima.jcep.dto;

import br.com.ernanilima.jcep.common.ComboBox;
import br.com.ernanilima.jcep.domain.Country;
import br.com.ernanilima.jcep.utils.Utils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.ernanilima.jcep.utils.Utils.getValueEnumType;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CountryDto implements Serializable {

    public static List<ComboBox> getComboBox(List<Country> countries) {
        return countries.stream().map(country ->
                        ComboBox.builder()
                                .id(country.getIdCountry())
                                .value(getValueEnumType(country.getName()))
                                .description(country.getName())
                                .code(Utils.toString(country.getCode()))
                                .build())
                .collect(Collectors.toList());
    }
}
