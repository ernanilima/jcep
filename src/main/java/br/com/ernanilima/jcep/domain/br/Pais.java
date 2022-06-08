package br.com.ernanilima.jcep.domain.br;

import br.com.ernanilima.jcep.domain.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static br.com.ernanilima.jcep.utils.Utils.toInteger;

@Getter
@AllArgsConstructor
public enum Pais {

    BRASIL("Brasil", "BR", "1058");

    private String name;
    private String acronym;
    private String code;

    public static Country getCountry() {
        return Country.builder()
                .name(Pais.BRASIL.getName())
                .acronym(Pais.BRASIL.getAcronym())
                .code(toInteger(Pais.BRASIL.getCode()))
                .build();
    }
}
