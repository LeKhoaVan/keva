package it.ktpm.keva.role.dto;

import it.ktpm.keva.user.dto.UserDTO;
import it.ktpm.keva.user.model.User;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class UserGroupWithUserDTO {
    private UUID id;

    @Length(min = 3, max = 10, message = "code in size min = {min} size max = {max}")
    private String code;

    @Length(min = 3, max = 10, message = "name in size min = {min} size max = {max}")
    private String name;

    @NotBlank
    private String description;

    private Set<UserDTO> users = new HashSet<>();
}
