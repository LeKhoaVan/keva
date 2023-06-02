package it.ktpm.keva.role.validation.validator;

import it.ktpm.keva.role.model.Role;
import it.ktpm.keva.role.repository.RoleRepository;
import it.ktpm.keva.role.validation.annotation.UniqueRoleName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueRoleNameValidator implements ConstraintValidator<UniqueRoleName,String> {
    private final RoleRepository roleRepository;
    private String message;

    public UniqueRoleNameValidator(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void initialize(UniqueRoleName constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Role role = roleRepository.findRolesByName(value);
        if (role == null){
            return true;
        }
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;

    }
}
