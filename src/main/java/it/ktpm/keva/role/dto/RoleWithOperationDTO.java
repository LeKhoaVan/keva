package it.ktpm.keva.role.dto;

import it.ktpm.keva.role.model.Operation;
import it.ktpm.keva.role.validation.annotation.UniqueRoleCode;
import it.ktpm.keva.role.validation.annotation.UniqueRoleName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class RoleWithOperationDTO {
    private UUID id;
    @UniqueRoleCode(message = "{role.code.exist}")
    @Size(min = 3, max = 10, message = "{role.code.size}")
    @NotBlank(message = "Code not null")
    private String code;

    @Size(min = 3, max = 10, message = "{role.name.size}")
    @NotBlank(message = "Name not null")
    @UniqueRoleName(message = "{role.name.exist}")
    private String name;

    @NotBlank(message = "{role.description.null}")
    private String description;

    private Operation.Type type;

    private Set<OperationDTO> operationDTOs;
}
