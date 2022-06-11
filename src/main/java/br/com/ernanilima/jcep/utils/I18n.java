package br.com.ernanilima.jcep.utils;

import org.springframework.context.i18n.LocaleContextHolder;

import java.util.ResourceBundle;

public class I18n {

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
