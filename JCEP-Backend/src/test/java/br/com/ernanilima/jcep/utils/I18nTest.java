package br.com.ernanilima.jcep.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;

import static br.com.ernanilima.jcep.utils.I18n.*;

class I18nTest {

    @Test
    @DisplayName("Retorna uma mensagem em en_US para o HttpStatus.UNPROCESSABLE_ENTITY (422)")
    void getMessageByStatusCode_Return_Message_en_US_For_Http_Status_UNPROCESSABLE_ENTITY() {
        Locale.setDefault(new Locale("en", "US"));
        int value = HttpStatus.UNPROCESSABLE_ENTITY.value();
        Assertions.assertEquals("Invalid value", I18n.getMessageByStatusCode(value));
    }

    @Test
    @DisplayName("Retorna uma mensagem em pt_BR para o HttpStatus.UNPROCESSABLE_ENTITY (422)")
    void getMessageByStatusCode_Return_Message_pt_BR_For_Http_Status_UNPROCESSABLE_ENTITY() {
        Locale.setDefault(new Locale("pt", "BR"));
        int value = HttpStatus.UNPROCESSABLE_ENTITY.value();
        Assertions.assertEquals("Valor inválido", I18n.getMessageByStatusCode(value));
    }

    @Test
    @DisplayName("Retorna uma mensagem em en_US para o HttpStatus.NOT_FOUND (404)")
    void getMessageByStatusCode_Return_Message_en_US_For_Http_Status_NOT_FOUND() {
        Locale.setDefault(new Locale("en", "US"));
        int value = HttpStatus.NOT_FOUND.value();
        Assertions.assertEquals("Not Found", I18n.getMessageByStatusCode(value));
    }

    @Test
    @DisplayName("Retorna uma mensagem em pt_BR para o HttpStatus.NOT_FOUND (404)")
    void getMessageByStatusCode_Return_Message_pt_BR_For_Http_Status_NOT_FOUND() {
        Locale.setDefault(new Locale("pt", "BR"));
        int value = HttpStatus.NOT_FOUND.value();
        Assertions.assertEquals("Não encontrado", I18n.getMessageByStatusCode(value));
    }

    @Test
    @DisplayName("Retorna uma mensagem em en_US para o HttpStatus.BAD_REQUEST (400)")
    void getMessageByStatusCode_Return_Message_en_US_For_Http_Status_BAD_REQUEST() {
        Locale.setDefault(new Locale("en", "US"));
        int value = HttpStatus.BAD_REQUEST.value();
        Assertions.assertEquals("Bad Request", I18n.getMessageByStatusCode(value));
    }

    @Test
    @DisplayName("Retorna uma mensagem em pt_BR para o HttpStatus.BAD_REQUEST (400)")
    void getMessageByStatusCode_Return_Message_pt_BR_For_Http_Status_BAD_REQUEST() {
        Locale.setDefault(new Locale("pt", "BR"));
        int value = HttpStatus.BAD_REQUEST.value();
        Assertions.assertEquals("Método não permitido", I18n.getMessageByStatusCode(value));
    }

    @Test
    @DisplayName("Retorna uma mensagem em pt_BR para ENDPOINT_NOT_FOUND (exc.endpoint.not.found)")
    void getMessage_Return_Message_pt_BR_For_ENDPOINT_NOT_FOUND() {
        Locale.setDefault(new Locale("pt", "BR"));
        String value = MessageFormat.format(I18n.getMessage(ENDPOINT_NOT_FOUND), "GET", "/start/end");
        Assertions.assertEquals("Endpoint indisponível ou não existe, GET /start/end", value);
    }

    @Test
    @DisplayName("Retorna uma mensagem em en_US para ENDPOINT_NOT_FOUND (exc.endpoint.not.found)")
    void getMessage_Return_Message_en_US_For_ENDPOINT_NOT_FOUND() {
        Locale.setDefault(new Locale("en", "US"));
        String value = MessageFormat.format(I18n.getMessage(ENDPOINT_NOT_FOUND), "GET", "/start/end");
        Assertions.assertEquals("Endpoint unavailable or does not exist, GET /start/end", value);
    }

    @Test
    @DisplayName("Retorna uma mensagem em pt_BR para METHOD_NOT_SUPPORTED (exc.method.not.supported)")
    void getMessage_Return_Message_pt_BR_For_METHOD_NOT_SUPPORTED() {
        Locale.setDefault(new Locale("pt", "BR"));
        String value = MessageFormat.format(I18n.getMessage(METHOD_NOT_SUPPORTED), "GET");
        Assertions.assertEquals("Método GET não é suportado", value);
    }

    @Test
    @DisplayName("Retorna uma mensagem em en_US para METHOD_NOT_SUPPORTED (exc.method.not.supported)")
    void getMessage_Return_Message_en_US_For_METHOD_NOT_SUPPORTED() {
        Locale.setDefault(new Locale("en", "US"));
        String value = MessageFormat.format(I18n.getMessage(METHOD_NOT_SUPPORTED), "GET");
        Assertions.assertEquals("Method GET is not supported", value);
    }

    @Test
    @DisplayName("Retorna uma mensagem em pt_BR para NOT_FOUND_ZIP_CODE (not.found.zip.code)")
    void getMessage_Return_Message_pt_BR_For_NOT_FOUND_ZIP_CODE() {
        Locale.setDefault(new Locale("pt", "BR"));
        String value = MessageFormat.format(I18n.getMessage(NOT_FOUND_ZIP_CODE), "12345678");
        Assertions.assertEquals("Não localizado o CEP 12345678", value);
    }

    @Test
    @DisplayName("Retorna uma mensagem em en_US para NOT_FOUND_ZIP_CODE (not.found.zip.code)")
    void getMessage_Return_Message_en_US_For_NOT_FOUND_ZIP_CODE() {
        Locale.setDefault(new Locale("en", "US"));
        String value = MessageFormat.format(I18n.getMessage(NOT_FOUND_ZIP_CODE), "12345678");
        Assertions.assertEquals("Zip code 12345678 not found", value);
    }

    @Test
    @DisplayName("Retorna uma mensagem em pt_BR para CEP invalido (invalid.zip.code)")
    void getMessage_Return_Message_pt_BR_For_Invalid_Zip_Code() {
        Locale.setDefault(new Locale("pt", "BR"));
        String value = I18n.getMessage("invalid.zip.code");
        Assertions.assertEquals("O CEP deve ter {length} caracteres numéricos", value);
    }

    @Test
    @DisplayName("Retorna uma mensagem em en_US para CEP invalido (invalid.zip.code)")
    void getMessage_Return_Message_en_US_For_Invalid_Zip_Code() {
        Locale.setDefault(new Locale("en", "US"));
        String value = I18n.getMessage("invalid.zip.code");
        Assertions.assertEquals("The zip code must have {length} numeric characters", value);
    }

    @Test
    @DisplayName("Retorna uma mensagem em pt_BR para linguagem invalida (invalid.language)")
    void getMessage_Return_Message_pt_BR_For_Invalid_Language() {
        Locale.setDefault(new Locale("pt", "BR"));
        String value = I18n.getMessage("invalid.language");
        Assertions.assertEquals("Idioma inválido, use 'en_US' ou 'pt_BR'", value);
    }

    @Test
    @DisplayName("Retorna uma mensagem em en_US para linguagem invalida (invalid.language)")
    void getMessage_Return_Message_en_US_For_Invalid_Language() {
        Locale.setDefault(new Locale("en", "US"));
        String value = I18n.getMessage("invalid.language");
        Assertions.assertEquals("Invalid language, use 'en_US' or 'pt_BR'", value);
    }

    @Test
    @DisplayName("Retorna uma MissingResourceException para um valor que nao existe no properties i18n (getMessageByStatusCode)")
    void getMessageByStatusCode_Return_MissingResourceException_For_Value_That_Doesnt_Exist_For_Properties_I18n() {
        Locale.setDefault(new Locale("pt", "BR"));
        int value = HttpStatus.MULTI_STATUS.value();
        Assertions.assertThrows(MissingResourceException.class, () ->I18n.getMessageByStatusCode(value));
    }

    @Test
    @DisplayName("Retorna uma MissingResourceException para uma linguagem nao existe para i18n (getMessageByStatusCode)")
    void getMessageByStatusCode_Return_MissingResourceException_For_Language_Does_Not_Exist_For_I18n() {
        Locale.setDefault(new Locale("es", "AR"));
        int value = HttpStatus.UNPROCESSABLE_ENTITY.value();
        Assertions.assertThrows(MissingResourceException.class, () ->I18n.getMessageByStatusCode(value));
    }

    @Test
    @DisplayName("Retorna uma MissingResourceException para um valor que nao existe no properties i18n (getMessage)")
    void getMessage_Return_MissingResourceException_For_Value_That_Doesnt_Exist_For_Properties_I18n() {
        Locale.setDefault(new Locale("pt", "BR"));
        String value = "value that does not exist in i18n properties";
        Assertions.assertThrows(MissingResourceException.class, () ->I18n.getMessage(value));
    }

    @Test
    @DisplayName("Retorna uma MissingResourceException para uma linguagem nao existe para i18n (getMessage)")
    void getMessage_Return_MissingResourceException_For_Language_Does_Not_Exist_For_I18n() {
        Locale.setDefault(new Locale("es", "AR"));
        String value = "invalid.language";
        Assertions.assertThrows(MissingResourceException.class, () ->I18n.getMessage(value));
    }
}