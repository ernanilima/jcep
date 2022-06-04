package br.com.ernanilima.jcep.utils;

public class Utils {

    public static Integer toInteger(String value) {
        if (value == null) { return null; }
        value = value.replaceAll("[^0-9]", "");
        if (value.equals("")) { return null; }
        return Integer.parseInt(value);
    }

    public static String toString(String value) {
        return (value == null || value.trim().isEmpty() ? null : value.trim());
    }
}
