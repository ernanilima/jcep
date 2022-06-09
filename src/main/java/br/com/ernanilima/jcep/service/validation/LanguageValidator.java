package br.com.ernanilima.jcep.service.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class LanguageValidator implements ConstraintValidator<Language, String> {

    private final String[] languages = new String[] { "en_US", "en-US", "pt_BR", "pt-BR" };

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        // nao pode ser nulo
        return s!= null &&
                // tem que ter 5 caracteres
                s.trim().length() == 5 &&
                // tem que ser igual ao solicitado em 'languages'
                Arrays.stream(languages).anyMatch(language -> language.equalsIgnoreCase(s));
    }
}
