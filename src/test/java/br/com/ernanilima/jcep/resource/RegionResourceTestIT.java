package br.com.ernanilima.jcep.resource;

import br.com.ernanilima.jcep.JCepTest;
import br.com.ernanilima.jcep.service.exception.RegionNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RegionResourceTestIT extends JCepTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Deve retornar uma lista de regioes para o pais")
    void findAllRegionByCountry_Must_Return_A_List_Of_Regions_For_The_Country() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/endereco/regiao")
                        .param("pais", "BR")
                        .contentType(MediaType.APPLICATION_JSON))

                // deve retornar o Status 200
                .andExpect(status().isOk())
                // deve retornar a lista de regioes
                .andExpect(jsonPath("$.*", hasSize(5)));
    }

    @Test
    @DisplayName("Deve retornar um erro para regioes nao encontradas para o pais")
    void findAllRegionByCountry_Must_Return_An_Error_For_Regions_Not_Found_For_The_Country() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/endereco/regiao")
                        .param("pais", "ER")
                        .contentType(MediaType.APPLICATION_JSON))

                // deve retornar o Status 404
                .andExpect(status().isNotFound())
                // deve retornar uma excecao 'RegionNotFoundException'
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RegionNotFoundException))
                // deve retornar a mensagem de orientacao do erro
                .andExpect(jsonPath("$.message", is("Não foi localizado as regiões para o país ER")));
    }
}