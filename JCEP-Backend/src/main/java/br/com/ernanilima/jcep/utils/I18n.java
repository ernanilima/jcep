package br.com.ernanilima.jcep.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.ResourceBundle;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class I18n {

    public static final String ENDPOINT_NOT_FOUND = "exc.endpoint.not.found";
    public static final String METHOD_NOT_SUPPORTED = "exc.method.not.supported";
    public static final String NOT_FOUND_ZIP_CODE = "not.found.zip.code";
    public static final String NOT_FOUND_REGION_BY_COUNTRY = "not.found.region.by.country";
    public static final String NOT_FOUND_STATE_BY_COUNTRY_OR_REGION = "not.found.state.by.country.or.region";
    public static final String NOT_FOUND_CITY_BY_COUNTRY_OR_REGION_OR_STATE = "not.found.city.by.country.or.region.or.state";


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
