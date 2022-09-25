package br.com.ernanilima.jcep.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UtilsTest {

    @Test
    @DisplayName("Retorna um Integer com os numeros da String")
    void toInteger_Return_Number() {
        String value = "Aa1BbCcD2dEeF3fGg4HhI5i!@6#$%7ˆˆ*8()_+=9-,.<>;0:[]{}|?";
        Assertions.assertEquals(1234567890, Utils.toInteger(value));
    }

    @Test
    @DisplayName("Retorna um Integer nulo para String nula")
    void toInteger_Return_Number_Null1() {
        Assertions.assertNull(Utils.toInteger(null));
    }

    @Test
    @DisplayName("Retorna um Integer nulo para String vazia")
    void toInteger_Return_Number_Null2() {
        Assertions.assertNull(Utils.toInteger(""));
    }

    @Test
    @DisplayName("Retorna um Integer nulo para String sem numero")
    void toInteger_Return_Number_Null3() {
        String value = "AaBbCcDdEeFfGgHhIi!@#$%ˆˆ*()_+=-,.<>;:[]{}|?";
        Assertions.assertNull(Utils.toInteger(value));
    }

    @Test
    @DisplayName("Retorna o numero em formato de String para a String passada no parametro")
    void toIntString_Return_Number_In_String_Format() {
        String value = "Aa1BbCcD2dEeF3fGg4HhI5i!@6#$%7ˆˆ*8()_+=9-,.<>;0:[]{}|?";
        Assertions.assertEquals("1234567890", Utils.toIntString(value));
    }

    @Test
    @DisplayName("Retorna uma String nula para a String nula passada no parametro")
    void toIntString_Return_Number_In_String_Format_Null1() {
        Assertions.assertNull(Utils.toIntString(null));
    }

    @Test
    @DisplayName("Retorna uma String nula para a String vazia passada no parametro")
    void toIntString_Return_Number_In_String_Format_Null2() {
        Assertions.assertNull(Utils.toIntString(""));
    }

    @Test
    @DisplayName("Retorna uma String nula para a String sem numero passada no parametro")
    void toIntString_Return_Number_In_String_Format_Null3() {
        String value = "AaBbCcDdEeFfGgHhIi!@#$%ˆˆ*()_+=-,.<>;:[]{}|?";
        Assertions.assertNull(Utils.toIntString(value));
    }

    @Test
    @DisplayName("Retorna a mensagem depois do primeiro caracte (teste1) ':'")
    void getSimpleErrorMessage_Return_Message_1() {
        String value = "findByZipCode.zipcode: O CEP deve ter 8 caracteres.";
        Assertions.assertEquals("O CEP deve ter 8 caracteres.", Utils.getSimpleErrorMessage(value));
    }

    @Test
    @DisplayName("Retorna a mensagem depois do primeiro caracte (teste2) varios caracteres na string ':'")
    void getSimpleErrorMessage_Return_Message_2() {
        String value = "o valor pode.ter;qualquer-dado_123_mas vai=retornar+apenas*depois%do:     tem que retornas isso.";
        Assertions.assertEquals("tem que retornas isso.", Utils.getSimpleErrorMessage(value));
    }

    @Test
    @DisplayName("Retorna a mensagem depois do primeiro caracte (teste3) varios caracteres na string e dois ':'")
    void getSimpleErrorMessage_Return_Message_3() {
        String value = "o valor pode.ter;qualquer-dado_123_mas vai=retornar+apenas*depois%do::     tem que retornas isso.";
        Assertions.assertEquals(":     tem que retornas isso.", Utils.getSimpleErrorMessage(value));
    }

    @Test
    @DisplayName("Retorna a mensagem depois do primeiro caracte (teste4) varios caracteres na string e sem ':'")
    void getSimpleErrorMessage_Return_Message_4() {
        String value = "o valor pode.ter;qualquer-dado_123_mas vai=retornar+apenas*depois%do     tem que retornas tudo.";
        Assertions.assertEquals(value, Utils.getSimpleErrorMessage(value));
    }

    @Test
    @DisplayName("Retorna uma NullPointerException para obter uma mensagem quando for enviado um valor nulo")
    void getSimpleErrorMessage_Return_Message_NullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> Utils.getSimpleErrorMessage(null));
    }

    @Test
    @DisplayName("Retorna a mensagem vazio para uma String vazio passada no parametro")
    void getSimpleErrorMessage_Return_Message_Empity() {
        String value = "";
        Assertions.assertEquals(value, Utils.getSimpleErrorMessage(value));
    }

    @Test
    @DisplayName("Retorna uma NullPointerException para obter o valor for enviado um valor nulo")
    void getValueEnumType_Return_Message_NullPointerException() {
        Assertions.assertThrows(NullPointerException.class, () -> Utils.getValueEnumType(null));
    }

    @Test
    @DisplayName("Retorna o valor tipo enum, sem acentos, espacos em underline e tudo em caixa alta")
    void getValueEnumType_Return_Message_1() {
        String value = "Sim, tudo é possível!";
        Assertions.assertEquals("SIM_TUDO_E_POSSIVEL", Utils.getValueEnumType(value));
    }
}