package it.ktpm.keva.project.model;

import it.ktpm.keva.common.models.EntityBase;
import it.ktpm.keva.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = ProjectUtils.Work.TABLE_NAME)
public class Work extends EntityBase {
    @Column(name = ProjectUtils.Work.CODE)
    private String code;
    @Column(name = ProjectUtils.Work.NAME)
    private String name;
    @Column(name = ProjectUtils.Work.DESCRIPTION)
    private String description;
    @Column(name = ProjectUtils.Work.STARTDAY)
    private LocalDateTime startDay;
    @Column(name = ProjectUtils.Work.ENDDAY)
    private LocalDateTime endDay;

    @Column(name = ProjectUtils.Work.STATUS, nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn(name = ProjectUtils.Work.ID_PROJECT)
    private Project project;
    @ManyToOne
    @JoinColumn(name = ProjectUtils.Work.ID_USER)
    private User user;


    public enum Status{
        ACTIVE,
        TEMPORARY_BLOCKED,
        BLOCKED
    }
}
