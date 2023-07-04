package it.ktpm.keva.role.model;

import it.ktpm.keva.common.models.EntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = RoleUtils.RoleTable.TABLE_NAME)
public class Role extends EntityBase {
    @Column(name = RoleUtils.RoleTable.CODE, unique = true)
    @Length(min = 3, max = 10, message = "code in size min = {min} size max = {max}")
    private String code;

    @Column(name = RoleUtils.RoleTable.NAME, unique = true)
    @Length(min = 3, max = 10, message = "name in size min = {min} size max = {max}")
    private String name;

    @Column(name = RoleUtils.RoleTable.DESCRIPTION)
    @NotBlank
    private String description;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = RoleUtils.RoleMappedOperation.JOIN_TABLE,
                joinColumns = @JoinColumn(name = RoleUtils.RoleMappedOperation.JOIN_TABLE_ROLE_ID),
                inverseJoinColumns = @JoinColumn(name = RoleUtils.RoleMappedOperation.JOIN_TABLE_OPERATION_ID))
    private Set<Operation> operations = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = RoleUtils.RoleMappedUserGroup.JOIN_TABLE,
            joinColumns = @JoinColumn(name = RoleUtils.RoleMappedUserGroup.JOIN_TABLE_ROLE_ID),
            inverseJoinColumns = @JoinColumn(name = RoleUtils.RoleMappedUserGroup.JOIN_TABLE_OPERATION_ID))
    private Set<UserGroup> userGroups = new HashSet<>();

    public void removeOperation(Operation operation){
        operations.remove(operation);
        operation.getRoles().remove(this);
    }

    public Role addOperation(Operation operation){
        this.operations.add(operation);
        operation.getRoles().add(this);
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Role role = (Role) o;
        return Objects.equals(this.id, role.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
