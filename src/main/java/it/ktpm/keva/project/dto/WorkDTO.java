package it.ktpm.keva.project.dto;

import it.ktpm.keva.project.model.Project;
import it.ktpm.keva.project.model.ProjectUtils;
import it.ktpm.keva.project.model.Work;
import it.ktpm.keva.user.model.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
public class WorkDTO {
    private UUID id;
    private String code;
    private String name;
    private String description;
    private LocalDateTime startDay;
    private LocalDateTime endDay;
    private Work.Status status;

    private UUID idProject;
    private UUID idUser;
}
