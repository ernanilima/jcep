package br.com.ernanilima.jcep.resource;

import br.com.ernanilima.jcep.JCepTest;
import br.com.ernanilima.jcep.service.exception.ZipCodeNoFoundException;
import br.com.ernanilima.jcep.utils.I18n;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.validation.ConstraintViolationException;

import java.text.MessageFormat;
import java.util.Locale;

import static br.com.ernanilima.jcep.utils.I18n.NOT_FOUND_ZIP_CODE;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql(statements = {
        "INSERT INTO country (id_country, acronym, code, name) " +
                "VALUES ('92ce8ea1-e7ac-4190-9469-2dded211dbad', 'TT', '1234', 'Teste')",
        "INSERT INTO region (id_region, name, country_id) " +
                "VALUES ('48769f32-5f80-49c1-8cfa-41eabf700dc6', 'Teste', '92ce8ea1-e7ac-4190-9469-2dded211dbad')",
        "INSERT INTO state (id_state, acronym, code, name, country_id, region_id) " +
                "VALUES ('8bac09ec-d534-4a1e-9b15-e36f3aa254ca', 'EE', '99', 'Teste', '92ce8ea1-e7ac-4190-9469-2dded211dbad', '48769f32-5f80-49c1-8cfa-41eabf700dc6');",
        "INSERT INTO city (id_city, area_code, code, name, country_id, region_id, state_id) " +
                "VALUES ('08205b24-a3f5-43de-9e46-7c4abad5e9c1', '99', '123456', 'Teste', '92ce8ea1-e7ac-4190-9469-2dded211dbad', '48769f32-5f80-49c1-8cfa-41eabf700dc6', '8bac09ec-d534-4a1e-9b15-e36f3aa254ca');",
        "INSERT INTO address (id_address, code, district, street, zip_code, city_id, country_id, region_id, state_id) " +
                "VALUES ('174f7482-8312-4a8b-ac46-2d4ed968e9d6', '99', 'Centro', 'Rua Principal', '33322333', '08205b24-a3f5-43de-9e46-7c4abad5e9c1', '92ce8ea1-e7ac-4190-9469-2dded211dbad', '48769f32-5f80-49c1-8cfa-41eabf700dc6', '8bac09ec-d534-4a1e-9b15-e36f3aa254ca');"
})
class AddressResourceTestIT extends JCepTest {

    @Autowired
    private MockMvc mockMvc;

    private final String invalidZipCode = "12345678";
    private final String incompleteZipCode = "1234567";
    private final String validZipCode = "01001000";
    private final String validZipCodeByDataBase = "33322333";
    private final String validZipCodeWithLetters = "Aa0Bb1Cc0Dd0!1@0#0$0%";

    @Test
    @DisplayName("Retorna um Status 200 e os dados do json para um cep encontrado no ViaCep")
    void findByZipCode_Return_Status_200_And_Data_For_Valid_Zip_Code() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/endereco/cep/{zipcode}", validZipCode)
                        .contentType(MediaType.APPLICATION_JSON))

                // tem que retornar o Status 200
                .andExpect(status().isOk())
                // tem que retornar o campo 'zipCode' com o valor '68721000'
                .andExpect(jsonPath("$.zipCode", is(validZipCode)));
    }

    @Test
    @DisplayName("Retorna um Status 200 e os dados do json para um cep(com letras removidas) encontrado no ViaCep")
    void findByZipCode_Return_Status_200_And_Data_For_Valid_Zip_Code_With_Letters() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/endereco/cep/{zipcode}", validZipCodeWithLetters)
                        .contentType(MediaType.APPLICATION_JSON))

                // tem que retornar o Status 200
                .andExpect(status().isOk())
                // tem que retornar o campo 'zipCode' com o valor '68721000'
                .andExpect(jsonPath("$.zipCode", is(validZipCode)));
    }

    @Test
    @DisplayName("Retorna um Status 200 e os dados do json para um cep encontrado no JCep")
    void findByZipCode_Return_Status_200_And_Data_For_Valid_Zip_Code_() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/endereco/cep/{zipcode}", validZipCodeByDataBase)
                        .contentType(MediaType.APPLICATION_JSON))

                // tem que retornar o Status 200
                .andExpect(status().isOk())
                // tem que retornar o campo 'zipCode' com o valor '68721000'
                .andExpect(jsonPath("$.zipCode", is(validZipCodeByDataBase)))
                // tem que retornar a consulta realizada no 'JCep'
                .andExpect(jsonPath("$.apiResult", is("JCep")));
    }

    @Test
    @DisplayName("Retorna um Status 404 e uma ZipCodeNoFoundException para um cep nao encontrado no ViaCep")
    void findByZipCode_Return_Status_404_And_ZipCodeNoFoundException_For_Invalid_Zip_Code() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/endereco/cep/{zipcode}", invalidZipCode)
                        .contentType(MediaType.APPLICATION_JSON))

                // tem que retornar o Status 404
                .andExpect(status().isNotFound())
                // tem que retornar uma excecao 'ZipCodeNoFoundException'
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof ZipCodeNoFoundException));
    }

    @Test
    @DisplayName("Retorna um Status 422 e uma ConstraintViolationException para um cep incompleto")
    void findByZipCode_Return_Status_422_And_ConstraintViolationException_For_Incomplete_Zip_Code() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/endereco/cep/{zipcode}", incompleteZipCode)
                        .contentType(MediaType.APPLICATION_JSON))

                // tem que retornar o Status 422
                .andExpect(status().isUnprocessableEntity())
                // tem que retornar uma excecao 'ConstraintViolationException'
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof ConstraintViolationException))
                // tem que retornar a mensagem de orientacao do erro
                .andExpect(jsonPath("$.message", is("O CEP deve ter 8 caracteres numéricos")));
    }

    @Test
    @DisplayName("Retorna um Status 404, ZipCodeNoFoundException e uma mensagem em ingles para um cep nao encontrado no ViaCep")
    void findByZipCode_Return_Status_404_And_ZipCodeNoFoundException_And_Message_In_English_For_Incomplete_Zip_Code() throws Exception {
        Locale.setDefault(new Locale("en", "US"));
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/endereco/cep/{zipcode}", invalidZipCode)
                        .param("language", "en_US")
                        .contentType(MediaType.APPLICATION_JSON))

                // tem que retornar o Status 404
                .andExpect(status().isNotFound())
                // tem que retornar uma excecao 'ZipCodeNoFoundException'
                .andExpect(result -> Assertions.assertTrue(result.getResolvedException() instanceof ZipCodeNoFoundException))
                // tem que retornar a mensagem de orientacao do erro
                .andExpect(jsonPath("$.message", is(MessageFormat.format(I18n.getMessage(NOT_FOUND_ZIP_CODE), invalidZipCode))));
    }
}