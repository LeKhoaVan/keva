package it.ktpm.keva.user.dto;

import it.ktpm.keva.user.model.User;
import it.ktpm.keva.user.validation.annotation.DistinctEmail;
import it.ktpm.keva.user.validation.annotation.DistinctUserName;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
public class UserDTO {
    private UUID id;
    @NotBlank
    @DistinctUserName(message = "{user.username.exist}")
    private String userName;
    @NotBlank
    private String displayName;
    private String fullName;
    private String avatar;
    private User.Status status;
    @NotBlank
    private String password;
    @DistinctEmail(message = "{user.email.exist}")
    private String email;

}
