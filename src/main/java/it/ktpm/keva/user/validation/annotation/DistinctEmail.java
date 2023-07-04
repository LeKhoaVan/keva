package it.ktpm.keva.user.validation.annotation;

import it.ktpm.keva.user.validation.validator.DistinctEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DistinctEmailValidator.class)
public @interface DistinctEmail {
    String message() default "email is exist!!!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
