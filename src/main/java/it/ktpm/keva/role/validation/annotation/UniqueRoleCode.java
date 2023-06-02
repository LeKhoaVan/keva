package it.ktpm.keva.role.validation.annotation;

import it.ktpm.keva.role.validation.validator.UniqueRoleCodeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueRoleCodeValidator.class)
public @interface UniqueRoleCode {
    String message() default "Role code exist!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
