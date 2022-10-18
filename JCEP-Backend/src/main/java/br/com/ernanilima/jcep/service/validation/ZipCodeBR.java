package br.com.ernanilima.jcep.service.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;

@Documented
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ZipCodeBRValidator.class)
public @interface ZipCodeBR {
    String message() default "Invalid zip code";

    int length() default 8;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
