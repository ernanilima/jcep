package br.com.ernanilima.jcep.repository;

import br.com.ernanilima.jcep.config.annotation.RepositoryTest;
import br.com.ernanilima.jcep.domain.Region;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@RepositoryTest
@Sql(scripts = {
        "classpath:/db/country.sql",
        "classpath:/db/region.sql"
})
class RegionRepositoryTestIT {

    @Autowired
    private RegionRepository regionRepository;

    @Test
    @DisplayName("Deve buscar todas as regioes")
    void findAll_Must_Search_All_Regions() {
        List<Region> regions = regionRepository.findAll();

        assertFalse(regions.isEmpty());
    }

    @Test
    @DisplayName("Deve buscar as regiões pela sigla do país")
    void findByCountryAcronym_Must_Search_For_Regions_By_Country_Acronym() {
        List<Region> regions;

        regions = regionRepository.findByCountry_Acronym("ZZ");
        assertTrue(regions.isEmpty());

        regions = regionRepository.findByCountry_Acronym("ZA");
        assertFalse(regions.isEmpty());
        assertEquals(regions.stream().filter(r -> r.getIdRegion().equals(UUID.fromString("c323e4ab-20f2-41fe-9613-132d1c9860c0")))
                        .map(Region::getName).findFirst(),
                Optional.of("Nome da regiao - 1"));
        assertEquals(regions.stream().filter(r -> r.getIdRegion().equals(UUID.fromString("d0b07eca-2cfa-4a4f-b761-0e6b8bca4078")))
                        .map(Region::getName).findFirst(),
                Optional.of("Nome da regiao - 2"));
    }
}