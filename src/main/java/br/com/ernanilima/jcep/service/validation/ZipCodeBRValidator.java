package br.com.ernanilima.jcep.service.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.util.Objects;

import static br.com.ernanilima.jcep.utils.Utils.toIntString;

public class ZipCodeBRValidator implements ConstraintValidator<ZipCodeBR, String> {

    private int length;

    @Override
    public void initialize(ZipCodeBR constraintAnnotation) {
        this.length = constraintAnnotation.length();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        // apenas os numeros
        String value = toIntString(s);
        return (Objects.nonNull(value) && isZipCode(value));
    }

    /**
     * O CEP recebido tem que ter o mesmo tamanho o solicitado na validacao.
     * @param s String - cep
     * @return boolean
     */
    private boolean isZipCode(String s) {
        return s.length() == length;
    }
}
