package it.ktpm.keva.role.validation.annotation;

import it.ktpm.keva.role.validation.validator.UniqueRoleNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueRoleNameValidator.class)
public @interface UniqueRoleName {
    String message() default "Role name exist!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
