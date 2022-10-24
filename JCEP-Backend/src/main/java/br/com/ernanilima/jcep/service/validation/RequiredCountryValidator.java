package br.com.ernanilima.jcep.service.validation;

import br.com.ernanilima.jcep.param.ParamCountry;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class RequiredCountryValidator implements
        ConstraintValidator<RequiredCountry, ParamCountry> {

    @Override
    public boolean isValid(ParamCountry param, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.nonNull(param.getPais());
    }
}
