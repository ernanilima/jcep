package br.com.ernanilima.jcep.builder;

import br.com.ernanilima.jcep.domain.Country;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CountryBuilder {

    public static Country create() {
        return Country.builder()
                .idCountry(UUID.fromString("08b12d81-1bc4-4a57-b010-1e28435442ae"))
                .name("Nome do pais")
                .acronym("ZA")
                .code(1234)
                .build();
    }
}
