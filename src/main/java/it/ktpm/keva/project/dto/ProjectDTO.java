package it.ktpm.keva.project.dto;

import it.ktpm.keva.project.model.Project;
import it.ktpm.keva.project.model.Work;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
public class ProjectDTO {
    private UUID id;
    private String code;

    private String name;

    private String description;

    private LocalDateTime startDay;
    private float time;

    private Project.Status status;

}
