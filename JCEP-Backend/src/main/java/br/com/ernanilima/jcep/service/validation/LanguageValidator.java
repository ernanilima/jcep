package br.com.ernanilima.jcep.service.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static java.util.Arrays.stream;

public class LanguageValidator implements ConstraintValidator<Language, String> {

    private final String[] languages = new String[]{"en_US", "en-US", "pt_BR", "pt-BR"};

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        // tem que ser igual ao solicitado em 'languages'
        return stream(languages).anyMatch(language -> language.equalsIgnoreCase(s));
    }
}
