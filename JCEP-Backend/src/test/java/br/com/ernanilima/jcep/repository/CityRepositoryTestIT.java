package br.com.ernanilima.jcep.repository;

import br.com.ernanilima.jcep.config.annotation.RepositoryTest;
import br.com.ernanilima.jcep.domain.City;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RepositoryTest
@Sql(scripts = {
        "classpath:/db/country.sql",
        "classpath:/db/region.sql",
        "classpath:/db/state.sql",
        "classpath:/db/city.sql"
})
class CityRepositoryTestIT {

    @Autowired
    private CityRepository cityRepository;

    @Test
    @DisplayName("Deve buscar a cidade pelo codigo do IBGE")
    void findByCode_Must_Search_For_The_City_By_The_IBGE_Code() {
        Optional<City> city = cityRepository.findByCode(9999991);

        assertTrue(city.isPresent());
        assertEquals(city.get().getName(), "Nome da cidade - 1");
        assertEquals(city.get().getCode(), 9999991);
        assertNotNull(city.get().getCountry());
        assertNotNull(city.get().getRegion());
        assertNotNull(city.get().getState());
    }
}