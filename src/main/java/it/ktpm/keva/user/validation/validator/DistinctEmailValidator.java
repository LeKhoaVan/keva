package it.ktpm.keva.user.validation.validator;

import it.ktpm.keva.user.model.User;
import it.ktpm.keva.user.service.UserService;
import it.ktpm.keva.user.validation.annotation.DistinctEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DistinctEmailValidator implements ConstraintValidator<DistinctEmail,String> {
    private String message;
    private final UserService userService;

    public DistinctEmailValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(DistinctEmail constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        User user = userService.findByEmail(value);
        if (user == null){
            return true;
        }
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
