package br.com.ernanilima.jcep.repository;

import br.com.ernanilima.jcep.config.annotation.RepositoryTest;
import br.com.ernanilima.jcep.domain.Country;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RepositoryTest
@Sql(scripts = {
        "classpath:/db/country.sql"
})
class CountryRepositoryTestIT {

    @Autowired
    private CountryRepository countryRepository;

    @Test
    @DisplayName("Deve buscar todos os paises")
    void findAll_Must_Search_All_Countries() {
        List<Country> countries = countryRepository.findAll();

        assertFalse(countries.isEmpty());
    }

    @Test
    @DisplayName("Deve buscar o pais pela sigla")
    void findByAcronym_Must_Search_For_The_Country_By_The_Acronym() {
        Optional<Country> country = countryRepository.findByAcronym("ZA");

        assertTrue(country.isPresent());
        assertEquals(country.get().getName(), "Nome do pais");
        assertEquals(country.get().getCode(), 1234);
    }
}