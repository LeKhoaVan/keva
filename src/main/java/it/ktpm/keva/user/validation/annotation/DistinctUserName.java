package it.ktpm.keva.user.validation.annotation;

import it.ktpm.keva.user.validation.validator.DistinctUserNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DistinctUserNameValidator.class)
public @interface DistinctUserName {
    String message() default "Username is exist!!!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
