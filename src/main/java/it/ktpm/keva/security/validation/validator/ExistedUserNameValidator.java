package it.ktpm.keva.security.validation.validator;

import it.ktpm.keva.common.exception.KevaBusinessException;
import it.ktpm.keva.security.validation.annotation.ExistedUserName;
import it.ktpm.keva.user.model.User;
import it.ktpm.keva.user.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExistedUserNameValidator implements ConstraintValidator<ExistedUserName,String> {
    private final UserService userService;
    private String message;

    public ExistedUserNameValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(ExistedUserName constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        User user = userService.findByUserName(value)
                .orElse(null);

        if(user != null){
            return true;
        }
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
