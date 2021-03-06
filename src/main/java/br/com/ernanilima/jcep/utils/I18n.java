package br.com.ernanilima.jcep.utils;

import org.springframework.context.i18n.LocaleContextHolder;

import java.util.ResourceBundle;

public class I18n {

    public static final String ENDPOINT_NOT_FOUND = "exc.endpoint.not.found";
    public static final String METHOD_NOT_SUPPORTED = "exc.method.not.supported";
    public static final String NOT_FOUND_ZIP_CODE = "not.found.zip.code";

    /**
     * @param statusCode int
     * @return String - mensagem
     */
    public static String getMessageByStatusCode(int statusCode) {
        return getMessage(String.valueOf(statusCode));
    }

    /**
     * @param s String
     * @return String - mensagem
     */
    public static String getMessage(String s) {
        return ResourceBundle.getBundle("messages", LocaleContextHolder.getLocale())
                .getString(s.toLowerCase());
    }
}
