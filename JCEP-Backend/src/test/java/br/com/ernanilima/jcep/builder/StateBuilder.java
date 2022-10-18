package br.com.ernanilima.jcep.builder;

import br.com.ernanilima.jcep.domain.State;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StateBuilder {

    public static State create() {
        return State.builder()
                .idState(UUID.fromString("a54efb4a-da79-404c-9ac5-0d71408c10b8"))
                .name("Nome do estado - 1")
                .acronym("ZB")
                .code(91)
                .country(CountryBuilder.create())
                .region(RegionBuilder.create())
                .build();
    }
}
