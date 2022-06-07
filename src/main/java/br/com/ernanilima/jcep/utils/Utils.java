package br.com.ernanilima.jcep.utils;

import java.util.Locale;
import java.util.ResourceBundle;

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

    public static String toString(String value) {
        return (value == null || value.trim().isEmpty() ? null : value.trim());
    }

    public static String toString(Integer value) {
        return (value == null ? null : String.valueOf(value));
    }

    public static String getSimpleErrorMessage(String value) {
        // exemplo: findByZipCode.zipcode: O CEP deve ter 8 caracteres.
        int startMessage = value.indexOf(":") + 1; // primeiro ':'
        return value.substring(startMessage).trim();
    }
}
