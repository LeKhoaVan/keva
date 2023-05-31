package it.ktpm.keva.role.model;

import it.ktpm.keva.common.models.EntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = RoleUtils.RoleTable.TABLE_NAME)
public class Role extends EntityBase {
    @Column(name = RoleUtils.RoleTable.CODE, unique = true)
    private String code;

    @Column(name = RoleUtils.RoleTable.NAME)
    private String name;

    @Column(name = RoleUtils.RoleTable.DESCRIPTION)
    private String description;
}
