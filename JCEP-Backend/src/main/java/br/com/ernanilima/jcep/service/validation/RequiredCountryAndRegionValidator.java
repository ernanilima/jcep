package br.com.ernanilima.jcep.service.validation;

import br.com.ernanilima.jcep.param.ParamCountryAndRegion;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class RequiredCountryAndRegionValidator implements ConstraintValidator<RequiredCountryAndRegion, ParamCountryAndRegion> {

    @Override
    public boolean isValid(ParamCountryAndRegion param, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.nonNull(param.getPais());
    }
}
