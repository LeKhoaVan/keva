package it.ktpm.keva.role.model;

import it.ktpm.keva.common.models.EntityBase;
import it.ktpm.keva.user.model.User;
import it.ktpm.keva.user.model.UserUtils;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
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
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = UserUtils.UserGroupMappedUser.JOIN_TABLE,
                joinColumns = @JoinColumn(name = UserUtils.UserGroupMappedUser.USERGROUP_ID),
                inverseJoinColumns = @JoinColumn(name = UserUtils.UserGroupMappedUser.USER_ID))
    private Set<User> users = new HashSet<>();

    public UserGroup addUser(User user){
        this.users.add(user);
        user.getUserGroups().add(this);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserGroup userGroup = (UserGroup) o;
        return Objects.equals(this.id, userGroup.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
