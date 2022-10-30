package br.com.ernanilima.jcep.resource;

import br.com.ernanilima.jcep.JCepTestIT;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static br.com.ernanilima.jcep.builder.PageableBuilder.getMultiPageable;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CountryResourceTestIT extends JCepTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Deve retornar uma lista paginada de paises")
    void findAllCountry_Must_Return_A_Paginated_List_Of_Countries() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/endereco/pais")
                        .params(getMultiPageable())
                        .contentType(MediaType.APPLICATION_JSON))

                // deve retornar o Status 200
                .andExpect(status().isOk())
                // deve retornar a lista de paises
                .andExpect(jsonPath("$.content.*", hasSize(12)))
                .andExpect(jsonPath("$.totalPages", is(2)))
                .andExpect(jsonPath("$.totalElements", is(13)));
    }
}