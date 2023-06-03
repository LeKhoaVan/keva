package it.ktpm.keva.role.model;

import it.ktpm.keva.common.models.EntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = RoleUtils.UserGroup.TABLE_NAME)
public class UserGroup extends EntityBase {
    @Column(name = RoleUtils.UserGroup.CODE, unique = true)
    @Length(min = 3, max = 10, message = "code in size min = {min} size max = {max}")
    private String code;

    @Column(name = RoleUtils.UserGroup.NAME, unique = true)
    @Length(min = 3, max = 10, message = "name in size min = {min} size max = {max}")
    private String name;

    @Column(name = RoleUtils.UserGroup.DESCRIPTION)
    @NotBlank
    private String description;

    @ManyToMany(mappedBy = RoleUtils.RoleMappedUserGroup.GROUP_MAPPED_ROLE)
    private Set<Role> roles;
}
