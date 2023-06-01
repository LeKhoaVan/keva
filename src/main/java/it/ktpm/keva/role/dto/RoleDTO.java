package it.ktpm.keva.role.dto;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class RoleDTO {
    private UUID id;
    private String code;
    private String name;
    private String description;
}
