package it.ktpm.keva.project.model;

import it.ktpm.keva.common.models.EntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = ProjectUtils.Project.TABLE_NAME)
public class Project extends EntityBase {
    @Column(name = ProjectUtils.Project.CODE, unique = true)
    private String code;

    @Column(name = ProjectUtils.Project.NAME)
    private String name;

    @Column(name = ProjectUtils.Project.DESCRIPTION)
    private String description;

    @Column(name = ProjectUtils.Project.STARTDAY)
    private LocalDateTime startDay;
    @Column(name = ProjectUtils.Project.TIME, nullable = false)
    private float time;

    @Column(name = ProjectUtils.Project.STATUS, nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "project", cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Set<Work> works;

    public void addWork(Work work){
        this.works.add(work);
    }

    public enum Status{
        ACTIVE,
        TEMPORARY_BLOCKED,
        BLOCKED
    }

}
