package it.ktpm.keva.user.model;

import it.ktpm.keva.common.models.EntityBase;
import it.ktpm.keva.role.model.UserGroup;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = UserUtils.User.TABLE_NAME)
public class User extends EntityBase {
    @Column(name = UserUtils.User.USER_NAME, length = 100, updatable = false, nullable = false, unique = true)
    private String userName;

    @Column(name = UserUtils.User.PASSWORD, length = 20, nullable = false)
    private String password;

    @Column(name = UserUtils.User.FULL_NAMNE, length = 50, nullable = false)
    private String fullName;

    @Column(name = UserUtils.User.DISPLAY_NAME, length = 50, nullable = false)
    private String displayName;

    @Column(name = UserUtils.User.EMAIL, length = 70, nullable = false, unique = true)
    private String email;

    @Column(name = UserUtils.User.AVATAR)
    private String avatar;

    @Column(name = UserUtils.User.STATUS, nullable = false)
    private Status status;

    @Column(name = UserUtils.User.FACEBOOK)
    private String faceBook;

    @Column(name = UserUtils.User.MAJORITY)
    private String majority;

    @Column(name = UserUtils.User.DEPARTMENT)
    private String department;

    @Column(name = UserUtils.User.HOBBY)
    private String hobby;

    @ManyToMany(mappedBy = UserUtils.UserGroupMappedUser.USER_MAP_USERGROUP)
    private Set<UserGroup> userGroups = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(this.id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public enum Status{
        ACTIVE,
        TEMPORARY_BLOCKED,
        PERMANENT_BLOCKED

    }
}
