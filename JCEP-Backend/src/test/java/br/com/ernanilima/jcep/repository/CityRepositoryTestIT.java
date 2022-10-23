package br.com.ernanilima.jcep.repository;

import br.com.ernanilima.jcep.builder.PageableBuilder;
import br.com.ernanilima.jcep.config.annotation.RepositoryTest;
import br.com.ernanilima.jcep.domain.City;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Test
    @DisplayName("Deve retornar as cidades para a sigla do país")
    void findAllByCountry_Acronym_Must_Return_Cities_To_Country_Acronym() {
        Pageable pageable = PageableBuilder.create();

        Page<City> cities = cityRepository.findAllByCountry_AcronymAndRegion_NameOrState_Acronym(
                "BR", null, null, pageable);

        assertNotNull(cities);
        assertEquals(cities.getTotalElements(), 5570L);
        assertEquals(cities.getContent().size(), 12);
        assertEquals(cities.getTotalPages(), 465);
    }

    @Test
    @DisplayName("Deve retornar as cidades para o nome da regiao")
    void findAllByCountry_AcronymAndRegion_Name_Must_Return_Cities_To_Region_Name() {
        Pageable pageable = PageableBuilder.create();

        Page<City> cities = cityRepository.findAllByCountry_AcronymAndRegion_NameOrState_Acronym(
                "BR", "NORTE", null, pageable);

        assertNotNull(cities);
        assertEquals(cities.getTotalElements(), 450L);
        assertEquals(cities.getContent().size(), 12);
        assertEquals(cities.getTotalPages(), 38);
    }

    @Test
    @DisplayName("Deve retornar as cidades para a sigla do estado")
    void findAllByCountry_AcronymAndState_Acronym_Must_Return_Cities_To_State_Acronym() {
        Pageable pageable = PageableBuilder.create();

        Page<City> cities = cityRepository.findAllByCountry_AcronymAndRegion_NameOrState_Acronym(
                "BR", null, "PA", pageable);assertNotNull(cities);
        assertEquals(cities.getTotalElements(), 144L);
        assertEquals(cities.getContent().size(), 12);
        assertEquals(cities.getTotalPages(), 12);
    }

    @Test
    @DisplayName("Deve retornar uma busca vazia por passar valores em branco")
    void findAllByEmptyValues_Must_Return_An_Empty_Search_By_Passing_Empty_Values() {
        Pageable pageable = PageableBuilder.create();

        Page<City> cities = cityRepository.findAllByCountry_AcronymAndRegion_NameOrState_Acronym(
                "", "", "", pageable);

        assertNotNull(cities);
        assertEquals(cities.getTotalElements(), 0L);
    }

    @Test
    @DisplayName("Deve retornar uma busca vazia por passar valores nulos")
    void findAllByNullValues_Must_Return_An_Empty_Search_By_Passing_Null_Values() {
        Pageable pageable = PageableBuilder.create();

        Page<City> cities = cityRepository.findAllByCountry_AcronymAndRegion_NameOrState_Acronym(
                null, null, null, pageable);

        assertNotNull(cities);
        assertEquals(cities.getTotalElements(), 0L);
    }

    @Test
    @DisplayName("Deve retornar uma busca vazia por passar apenas o nome da regia")
    void findAllByRegion_Name_Must_Return_An_Empty_Search_By_Passing_Only_The_Region_Name() {
        Pageable pageable = PageableBuilder.create();

        Page<City> cities = cityRepository.findAllByCountry_AcronymAndRegion_NameOrState_Acronym(
                null, "NORTE", null, pageable);

        assertNotNull(cities);
        assertEquals(cities.getTotalElements(), 0L);
    }

    @Test
    @DisplayName("Deve retornar uma busca vazia por passar apenas a sigla do estado")
    void findAllByState_Acronym_Must_Return_An_Empty_Search_By_Passing_Only_The_State_Acronym() {
        Pageable pageable = PageableBuilder.create();

        Page<City> cities = cityRepository.findAllByCountry_AcronymAndRegion_NameOrState_Acronym(
                null, null, "PA", pageable);

        assertNotNull(cities);
        assertEquals(cities.getTotalElements(), 0L);
    }
}