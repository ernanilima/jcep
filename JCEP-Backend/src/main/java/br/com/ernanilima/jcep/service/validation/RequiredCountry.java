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
@Constraint(validatedBy = RequiredCountryValidator.class)
public @interface RequiredCountry {
    String message() default "Invalid parameter";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
