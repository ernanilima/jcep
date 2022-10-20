package br.com.ernanilima.jcep.service.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Documented
@Target({ TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RequiredCountryAndRegionValidator.class)
public @interface RequiredCountryAndRegion {
    String message() default "{exc.required.country.and.region}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
