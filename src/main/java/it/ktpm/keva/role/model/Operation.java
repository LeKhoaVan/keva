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
@Table(name = RoleUtils.Operation.TABLE_NAME)
public class Operation extends EntityBase {
    @Column(name = RoleUtils.Operation.CODE, unique = true)
    @Length(min = 3, max = 10, message = "code in size min = {min} size max = {max}")
    private String code;

    @Column(name = RoleUtils.Operation.NAME, unique = true)
    @Length(min = 3, max = 10, message = "name in size min = {min} size max = {max}")
    private String name;

    @Column(name = RoleUtils.Operation.DESCRIPTION)
    @NotBlank
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = RoleUtils.Operation.type, nullable = false)
    private Type type;

    @ManyToMany(mappedBy = RoleUtils.RoleMappedOperation.SERVICE_MAPPED_ROLE)
    private Set<Role> roles = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Operation operation = (Operation) o;
        return Objects.equals(this.id, operation.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public enum Type{
        SAVE_OR_UPDATE,
        FETCH,
        REMOVE
    }
}
