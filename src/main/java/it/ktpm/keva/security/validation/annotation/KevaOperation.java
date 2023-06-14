package it.ktpm.keva.security.validation.annotation;

import it.ktpm.keva.role.model.Operation;
import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


//annotation danh dau de phan quyen
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface KevaOperation {
    String name() default "";

    Operation.Type type() default Operation.Type.FETCH;

}
