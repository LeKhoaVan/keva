package it.ktpm.keva.user.dto;

import it.ktpm.keva.user.model.User;
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
    private String userName;
    private String displayName;
    private String fullName;
    private String avatar;
    private User.Status status;
    private String password;
    private String email;

}
