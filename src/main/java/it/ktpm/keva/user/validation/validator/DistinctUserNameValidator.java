package it.ktpm.keva.user.validation.validator;

import it.ktpm.keva.user.model.User;
import it.ktpm.keva.user.service.UserService;
import it.ktpm.keva.user.validation.annotation.DistinctUserName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DistinctUserNameValidator implements ConstraintValidator<DistinctUserName,String> {
    private String message;
    private final UserService userService;

    public DistinctUserNameValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(DistinctUserName constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        User user = userService.findByUserName(value).orElse(null);

        if (user == null){
            return true;
        }

        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
