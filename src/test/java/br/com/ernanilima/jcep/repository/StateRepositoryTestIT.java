package br.com.ernanilima.jcep.repository;

import br.com.ernanilima.jcep.builder.PageableBuilder;
import br.com.ernanilima.jcep.config.annotation.RepositoryTest;
import br.com.ernanilima.jcep.domain.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RepositoryTest
@Sql(scripts = {
        "classpath:/db/country.sql",
        "classpath:/db/region.sql",
        "classpath:/db/state.sql"
})
class StateRepositoryTestIT {

    @Autowired
    private StateRepository stateRepository;

    @Test
    @DisplayName("Deve retornar um estado pela sigla")
    void findByAcronym_Must_Return_A_State_By_The_Acronym() {
        State state = stateRepository.findByAcronym("ZB");

        assertNotNull(state);
        assertEquals(state.getIdState(), UUID.fromString("a54efb4a-da79-404c-9ac5-0d71408c10b8"));
        assertEquals(state.getName(), "Nome do estado - 1");
        assertEquals(state.getAcronym(), "ZB");
        assertEquals(state.getCode(), 91);
        assertNotNull(state.getCountry());
        assertNotNull(state.getRegion());
    }

    @Test
    @DisplayName("Deve retornar os estados pela sigla do pais")
    void findAllByCountry_AcronymOrRegion_NameIgnoreCase_Must_Return_States_By_Country_Acronym() {
        Pageable pageable = PageableBuilder.create();

        Page<State> states = stateRepository.findAllByCountry_AcronymOrRegion_NameIgnoreCase("BR", null, pageable);

        assertNotNull(states);
        assertEquals(states.getTotalElements(), 27L);
        assertEquals(states.getContent().size(), 12);
        assertEquals(states.getTotalPages(), 3);
    }

    @Test
    @DisplayName("Deve retornar os estados pelo nome da regiao")
    void findAllByCountry_AcronymOrRegion_NameIgnoreCase_Must_Return_States_By_Region_Name() {
        Pageable pageable = PageableBuilder.create();

        Page<State> states = stateRepository.findAllByCountry_AcronymOrRegion_NameIgnoreCase( null, "NORTE", pageable);

        assertNotNull(states);
        assertEquals(states.getTotalElements(), 7L);
        assertEquals(states.getContent().size(), 7);
        assertEquals(states.getTotalPages(), 1);
    }
}