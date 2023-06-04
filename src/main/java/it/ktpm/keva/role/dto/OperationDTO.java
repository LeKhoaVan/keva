package it.ktpm.keva.role.dto;

import it.ktpm.keva.role.model.Operation;
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
public class OperationDTO {

    private UUID id;

    @Size(min = 3, max = 10, message = "{role.code.size}")
    private String code;


    @Size(min = 3, max = 10, message = "{role.code.size}")
    private String name;


    @NotBlank(message = "Description not null")
    private String description;

    Operation.Type type;

}
