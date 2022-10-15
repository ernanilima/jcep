package br.com.ernanilima.jcep.service.validation;

import br.com.ernanilima.jcep.dto.CountryOrRegionDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class RequiredCountryOrRegionValidator implements ConstraintValidator<RequiredCountryOrRegion, CountryOrRegionDto> {

    @Override
    public boolean isValid(CountryOrRegionDto param, ConstraintValidatorContext constraintValidatorContext) {
        return !(Objects.isNull(param.getPais()) && Objects.isNull(param.getRegiao()) ||
                Objects.nonNull(param.getPais()) && Objects.nonNull(param.getRegiao()));
    }
}
