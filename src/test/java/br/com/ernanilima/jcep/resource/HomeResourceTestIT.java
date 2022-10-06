package br.com.ernanilima.jcep.resource;

import br.com.ernanilima.jcep.JCepTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.NoHandlerFoundException;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class HomeResourceTestIT extends JCepTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Deve redirecionar para home do swagger")
    void home_Must_Redirect_To_Swagger_Home() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/")
                        .contentType(MediaType.APPLICATION_JSON))

                // deve retornar o Status 302
                .andExpect(status().isFound())
                // deve retornar a nova uri
                .andExpect(redirectedUrl("swagger-ui/index.html"));
    }

    @Test
    @DisplayName("Deve retornar um erro para endpoint nao encontrado/existe")
    void home_Must_Return_An_Error_For_Endpoint_Not_Found_Exists() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/endpoint-que-nao-existe")
                        .contentType(MediaType.APPLICATION_JSON))

                // deve retornar o Status 404
                .andExpect(status().isNotFound())
                // deve retornar uma excecao 'NoHandlerFoundException'
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NoHandlerFoundException))
                // deve retornar a mensagem de orientacao do erro
                .andExpect(jsonPath("$.message", is("Endpoint indisponível ou não existe, GET /endpoint-que-nao-existe")))
                // deve retornar a uri nao encontrado/existe
                .andExpect(jsonPath("$.path", is("/endpoint-que-nao-existe")));
    }
}