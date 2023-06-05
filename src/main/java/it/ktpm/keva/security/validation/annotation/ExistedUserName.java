package it.ktpm.keva.security.validation.annotation;

import it.ktpm.keva.security.validation.validator.ExistedUserNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistedUserNameValidator.class)
public @interface ExistedUserName {
    String message() default "UserName not existed!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
