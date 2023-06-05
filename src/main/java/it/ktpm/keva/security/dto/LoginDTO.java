package it.ktpm.keva.security.dto;

import it.ktpm.keva.security.validation.annotation.ExistedUserName;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    @ExistedUserName
    private String userName;
    private String password;
}
