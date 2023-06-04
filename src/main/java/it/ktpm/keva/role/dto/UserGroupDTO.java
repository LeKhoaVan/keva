package it.ktpm.keva.role.dto;

import it.ktpm.keva.role.model.RoleUtils;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class UserGroupDTO {
    private UUID id;

    @Length(min = 3, max = 10, message = "code in size min = {min} size max = {max}")
    private String code;

    @Length(min = 3, max = 10, message = "name in size min = {min} size max = {max}")
    private String name;

    @NotBlank
    private String description;


}
