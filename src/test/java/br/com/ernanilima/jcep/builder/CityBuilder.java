package br.com.ernanilima.jcep.builder;

import br.com.ernanilima.jcep.domain.City;

import java.util.UUID;

public class CityBuilder {

    public static City create() {
        return City.builder()
                .idCity(UUID.fromString("fc4be8e2-8522-4d82-8929-45c3ea698392"))
                .name("Nome da cidade - 1")
                .code(9999991)
                .areaCode(null)
                .country(CountryBuilder.create())
                .region(RegionBuilder.create())
                .state(StateBuilder.create())
                .build();
    }
}
