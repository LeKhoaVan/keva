package it.ktpm.keva.role.model;

import it.ktpm.keva.common.models.EntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = RoleUtils.Module.TABLE_NAME)
public class Module extends EntityBase {
    @Column(name = RoleUtils.Module.CODE, unique = true)
    @Length(min = 3, max = 10, message = "code in size min = {min} size max = {max}")
    private String code;

    @Column(name = RoleUtils.Module.NAME, unique = true)
    @Length(min = 3, max = 10, message = "name in size min = {min} size max = {max}")
    private String name;

    @Column(name = RoleUtils.Module.DESCRIPTION)
    @NotBlank
    private String description;

}
