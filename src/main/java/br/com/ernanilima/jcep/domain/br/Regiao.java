package br.com.ernanilima.jcep.domain.br;

import br.com.ernanilima.jcep.domain.Country;
import br.com.ernanilima.jcep.domain.Region;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public enum Regiao {

    NORTE("Norte"),
    NORDESTE("Nordeste"),
    CENTRO_OESTE("Centro-Oeste"),
    SUDESTE("Sudeste"),
    SUL("Sul");

    private String name;

    public static List<Region> getRegions(Country country) {
        List<Region> regions = new ArrayList<>();
        for (Regiao value : Regiao.values()) {
            regions.add(
                    Region.builder()
                            .name(value.getName())
                            .country(country)
                    .build()
            );
        }
        return regions;
    }
}
