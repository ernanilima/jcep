package br.com.ernanilima.jcep.resource;

import br.com.ernanilima.jcep.JCepTestIT;
import br.com.ernanilima.jcep.service.exception.StateNotFoundException;
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

class StateResourceTestIT extends JCepTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Deve retornar uma lista de estados para o pais")
    void findAllStateByCountryOrRegion_Must_Return_A_List_Of_States_For_The_Country() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/endereco/estado")
                        .param("pais", "BR")
                        .params(getMultiPageable())
                        .contentType(MediaType.APPLICATION_JSON))

                // deve retornar o Status 200
                .andExpect(status().isOk())
                // deve retornar a lista de estados para o pais
                .andExpect(jsonPath("$.content.*", hasSize(12)));
    }

    @Test
    @DisplayName("Deve retornar uma lista de estados para a regiao")
    void findAllStateByCountryOrRegion_Must_Return_A_List_Of_States_For_The_Region() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/endereco/estado")
                        .param("regiao", "SUL")
                        .params(getMultiPageable())
                        .contentType(MediaType.APPLICATION_JSON))

                // deve retornar o Status 200
                .andExpect(status().isOk())
                // deve retornar a lista de estados para a regiao
                .andExpect(jsonPath("$.content.*", hasSize(3)));
    }

    @Test
    @DisplayName("Deve retornar um erro por passar dois parametros de filtro")
    void findAllStateByCountryOrRegion_Must_Return_An_Error_For_Passing_Two_Filter_Parameters() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/endereco/estado")
                        .param("pais", "BR")
                        .param("regiao", "SUL")
                        .params(getMultiPageable())
                        .contentType(MediaType.APPLICATION_JSON))

                // deve retornar o Status 422
                .andExpect(status().isUnprocessableEntity())
                // deve retornar uma excecao 'BindException'
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BindException))
                // deve retornar a mensagem de orientacao do erro
                .andExpect(jsonPath("$.message", is("Informe o País ou Região")));
    }

    @Test
    @DisplayName("Deve retornar um erro por nao passar nenum parametros de filtro")
    void findAllStateByCountryOrRegion_Must_Return_An_Error_For_Not_Passing_Any_Filter_Parameters() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/endereco/estado")
                        .params(getMultiPageable())
                        .contentType(MediaType.APPLICATION_JSON))

                // deve retornar o Status 422
                .andExpect(status().isUnprocessableEntity())
                // deve retornar uma excecao 'BindException'
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BindException))
                // deve retornar a mensagem de orientacao do erro
                .andExpect(jsonPath("$.message", is("Informe o País ou Região")));
    }

    @Test
    @DisplayName("Deve retornar um erro por nao encontrar nenhum estado para o pais")
    void findAllStateByCountryOrRegion_Must_Return_An_Error_For_Not_Finding_Any_State_For_The_Country() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/endereco/estado")
                        .param("pais", "AR")
                        .params(getMultiPageable())
                        .contentType(MediaType.APPLICATION_JSON))

                // deve retornar o Status 404
                .andExpect(status().isNotFound())
                // deve retornar uma excecao 'StateNotFoundException'
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof StateNotFoundException))
                // deve retornar a mensagem de orientacao do erro
                .andExpect(jsonPath("$.message", is("Não foi localizado nenhum Estado")));
    }

    @Test
    @DisplayName("Deve retornar um erro por nao encontrar nenhum estado para a regiao")
    void findAllStateByCountryOrRegion_Must_Return_An_Error_For_Not_Finding_Any_State_For_The_Region() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/endereco/estado")
                        .param("regiao", "CENTER")
                        .params(getMultiPageable())
                        .contentType(MediaType.APPLICATION_JSON))

                // deve retornar o Status 404
                .andExpect(status().isNotFound())
                // deve retornar uma excecao 'StateNotFoundException'
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof StateNotFoundException))
                // deve retornar a mensagem de orientacao do erro
                .andExpect(jsonPath("$.message", is("Não foi localizado nenhum Estado")));
    }
}