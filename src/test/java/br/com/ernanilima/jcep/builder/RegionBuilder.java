package br.com.ernanilima.jcep.builder;

import br.com.ernanilima.jcep.domain.Region;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RegionBuilder {

    public static Region create() {
        return Region.builder()
                .idRegion(UUID.fromString("c323e4ab-20f2-41fe-9613-132d1c9860c0"))
                .name("Nome da regiao - 1")
                .country(CountryBuilder.create())
                .build();
    }
}
