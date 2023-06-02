package it.ktpm.keva.role.validation.validator;

import it.ktpm.keva.role.model.Role;
import it.ktpm.keva.role.repository.RoleRepository;
import it.ktpm.keva.role.validation.annotation.UniqueRoleCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueRoleCodeValidator implements ConstraintValidator<UniqueRoleCode,String> {
    private final RoleRepository roleRepository;
    private String message;
    public UniqueRoleCodeValidator(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // get message
    @Override
    public void initialize(UniqueRoleCode constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Role role = roleRepository.findByCode(value);
        if(role == null){
            return true;
        }
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
