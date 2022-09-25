package br.com.ernanilima.jcep.utils;

import java.text.Normalizer;
import java.util.Objects;

public class Utils {

    public static Integer toInteger(String value) {
        if (value == null) { return null; }
        value = value.replaceAll("[^0-9]", "");
        if (value.equals("")) { return null; }
        return Integer.parseInt(value);
    }

    public static String toIntString(String value) {
        if (value == null) { return null; }
        value = value.replaceAll("[^0-9]", "");
        if (value.equals("")) { return null; }
        return value;
    }

    public static String toString(Integer value) {
        return (value == null ? null : String.valueOf(value));
    }

    public static String getSimpleErrorMessage(String value) {
        Objects.requireNonNull(value, "Value não pode ser nulo");
        // exemplo: findByZipCode.zipcode: O CEP deve ter 8 caracteres.
        int startMessage = value.indexOf(":") + 1; // primeiro ':'
        return value.substring(startMessage).trim();
    }

    public static String getValueEnumType(String value) {
        Objects.requireNonNull(value, "Value não pode ser nulo");
        // exemplo: Recebido 'Sim, tudo é possível' / Retorno 'SIM_TUDO_E_POSSIVEL'
        String normalizer = Normalizer.normalize(value, Normalizer.Form.NFKD);
        return normalizer.replaceAll("\\p{M}", "")
                .replaceAll("[^a-zA-Z\\s]+", "")
                .replaceAll("\\s", "_")
                .toUpperCase();
    }

    //public static String getMessageMethodError(String message) {
    //    // exemplo: No handler found for POST /endereco/cep
    //    System.out.println(message);
    //    int startEndpoint = message.indexOf("/"); // primeira '/'
    //    int endMessage = message.lastIndexOf(" ", startEndpoint - 2); // final da mensagem e inicio do method
    //    return message.substring(0, endMessage);
    //}
}
