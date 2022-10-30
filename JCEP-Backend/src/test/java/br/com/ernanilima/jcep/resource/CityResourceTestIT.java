package br.com.ernanilima.jcep.resource;

import br.com.ernanilima.jcep.JCepTestIT;
import br.com.ernanilima.jcep.service.exception.CityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.validation.BindException;

import static br.com.ernanilima.jcep.builder.PageableBuilder.getMultiPageable;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CityResourceTestIT extends JCepTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Deve retornar uma lista de cidades para o pais")
    void findAllCityByCountry_Must_Return_A_List_Of_Cities_For_The_Country() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/endereco/cidade")
                        .param("pais", "BR")
                        .params(getMultiPageable())
                        .contentType(MediaType.APPLICATION_JSON))

                // deve retornar o Status 200
                .andExpect(status().isOk())
                // deve retornar a lista de cidades para o pais
                .andExpect(jsonPath("$.content.*", hasSize(12)))
                .andExpect(jsonPath("$.totalPages", is(465)))
                .andExpect(jsonPath("$.totalElements", is(5570)));
    }

    @Test
    @DisplayName("Deve retornar uma lista de cidades para o pais e regiao")
    void findAllCityByCountryAndRegion_Must_Return_A_List_Of_Cities_For_The_Country_And_Region() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/endereco/cidade")
                        .param("pais", "BR")
                        .param("regiao", "NORTE")
                        .params(getMultiPageable())
                        .contentType(MediaType.APPLICATION_JSON))

                // deve retornar o Status 200
                .andExpect(status().isOk())
                // deve retornar a lista de cidades para o pais e regiao
                .andExpect(jsonPath("$.content.*", hasSize(12)))
                .andExpect(jsonPath("$.totalPages", is(38)))
                .andExpect(jsonPath("$.totalElements", is(450)));
    }

    @Test
    @DisplayName("Deve retornar uma lista de cidades para o pais, regiao e estado")
    void findAllCityByCountryAndRegionAndState_Must_Return_A_List_Of_Cities_For_The_Country_And_Region_And_State() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/endereco/cidade")
                        .param("pais", "BR")
                        .param("regiao", "NORTE")
                        .param("estado", "PA")
                        .params(getMultiPageable())
                        .contentType(MediaType.APPLICATION_JSON))

                // deve retornar o Status 200
                .andExpect(status().isOk())
                // deve retornar a lista de cidades para o pais, regiao e estado
                .andExpect(jsonPath("$.content.*", hasSize(12)))
                .andExpect(jsonPath("$.totalPages", is(12)))
                .andExpect(jsonPath("$.totalElements", is(144)));
    }

    @Test
    @DisplayName("Deve retornar um erro por nao passar nenum parametros de filtro")
    void findAllCityNoParameters_Must_Return_An_Error_For_Not_Passing_Any_Filter_Parameters() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/endereco/cidade")
                        .params(getMultiPageable())
                        .contentType(MediaType.APPLICATION_JSON))

                // deve retornar o Status 422
                .andExpect(status().isUnprocessableEntity())
                // deve retornar uma excecao 'BindException'
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BindException))
                // deve retornar a mensagem de orientacao do erro
                .andExpect(jsonPath("$.message", is("Informe o País, País e Região ou País e Estado")));
    }

    @Test
    @DisplayName("Deve retornar um erro por passar apenas a regiao")
    void findAllCityByOnlyRegion_Must_Return_An_Error_For_Passing_Only_The_Region() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/endereco/cidade")
                        .param("regiao", "NORTE")
                        .params(getMultiPageable())
                        .contentType(MediaType.APPLICATION_JSON))

                // deve retornar o Status 422
                .andExpect(status().isUnprocessableEntity())
                // deve retornar uma excecao 'BindException'
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BindException))
                // deve retornar a mensagem de orientacao do erro
                .andExpect(jsonPath("$.message", is("Informe o País, País e Região ou País e Estado")));
    }

    @Test
    @DisplayName("Deve retornar um erro por passar apenas o estado")
    void findAllCityByOnlyState_Must_Return_An_Error_For_Passing_Only_The_State() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/endereco/cidade")
                        .param("estado", "PA")
                        .params(getMultiPageable())
                        .contentType(MediaType.APPLICATION_JSON))

                // deve retornar o Status 422
                .andExpect(status().isUnprocessableEntity())
                // deve retornar uma excecao 'BindException'
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BindException))
                // deve retornar a mensagem de orientacao do erro
                .andExpect(jsonPath("$.message", is("Informe o País, País e Região ou País e Estado")));
    }

    @Test
    @DisplayName("Deve retornar um erro por nao passar o pais")
    void findAllCityByRegionAndState_Must_Return_An_Error_For_Not_Passing_The_Country() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/endereco/cidade")
                        .param("regiao", "NORTE")
                        .param("estado", "PA")
                        .params(getMultiPageable())
                        .contentType(MediaType.APPLICATION_JSON))

                // deve retornar o Status 422
                .andExpect(status().isUnprocessableEntity())
                // deve retornar uma excecao 'BindException'
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BindException))
                // deve retornar a mensagem de orientacao do erro
                .andExpect(jsonPath("$.message", is("Informe o País, País e Região ou País e Estado")));
    }

    @Test
    @DisplayName("Deve retornar um erro por nao encontrar nenhuma cidade para o pais")
    void findAllCityByCountry_Must_Return_An_Error_For_Not_Finding_Any_City_For_The_Country() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/endereco/cidade")
                        .param("pais", "AR")
                        .params(getMultiPageable())
                        .contentType(MediaType.APPLICATION_JSON))

                // deve retornar o Status 404
                .andExpect(status().isNotFound())
                // deve retornar uma excecao 'CityNotFoundException'
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof CityNotFoundException))
                // deve retornar a mensagem de orientacao do erro
                .andExpect(jsonPath("$.message", is("Não foi localizado nenhuma Cidade")));
    }

    @Test
    @DisplayName("Deve retornar um erro por nao encontrar nenhuma cidade para o pais e regiao")
    void findAllCityByCountryAndRegion_Must_Return_An_Error_For_Not_Finding_Any_City_For_The_Country_And_Region() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/endereco/cidade")
                        .param("pais", "BR")
                        .param("regiao", "CENTER")
                        .params(getMultiPageable())
                        .contentType(MediaType.APPLICATION_JSON))

                // deve retornar o Status 404
                .andExpect(status().isNotFound())
                // deve retornar uma excecao 'CityNotFoundException'
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof CityNotFoundException))
                // deve retornar a mensagem de orientacao do erro
                .andExpect(jsonPath("$.message", is("Não foi localizado nenhuma Cidade")));
    }

    @Test
    @DisplayName("Deve retornar um erro por nao encontrar nenhuma cidade para o pais, regiao e estado")
    void findAllCityByCountryAndRegionAndState_Must_Return_An_Error_For_Not_Finding_Any_City_For_The_Country_And_Region_And_State() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/endereco/cidade")
                        .param("pais", "BR")
                        .param("regiao", "NORTE")
                        .param("estado", "ZZ")
                        .params(getMultiPageable())
                        .contentType(MediaType.APPLICATION_JSON))

                // deve retornar o Status 404
                .andExpect(status().isNotFound())
                // deve retornar uma excecao 'CityNotFoundException'
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof CityNotFoundException))
                // deve retornar a mensagem de orientacao do erro
                .andExpect(jsonPath("$.message", is("Não foi localizado nenhuma Cidade")));
    }
}