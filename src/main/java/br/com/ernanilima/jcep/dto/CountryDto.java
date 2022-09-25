package br.com.ernanilima.jcep.dto;

import br.com.ernanilima.jcep.domain.Country;
import br.com.ernanilima.jcep.utils.Utils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static br.com.ernanilima.jcep.utils.Utils.getValueEnumType;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CountryDto implements Serializable {

    public static List<ComboBox> getComboBox(List<Country> countries) {
        return countries.stream().map(country ->
                        ComboBox.builder()
                                .idCountry(country.getIdCountry())
                                .value(getValueEnumType(country.getName()))
                                .description(country.getName())
                                .code(Utils.toString(country.getCode()))
                                .build())
                .collect(Collectors.toList());
    }

    @Builder
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ComboBox {
        private UUID idCountry;
        private String value;
        private String description;
        private String code;
    }
}
