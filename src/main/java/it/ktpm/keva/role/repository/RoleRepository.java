package it.ktpm.keva.role.repository;

import it.ktpm.keva.role.model.Role;
import it.ktpm.keva.role.model.RoleUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role findByCode(String code);

    Role deleteByCode(String code);

    @Query(value = "select * from k_role r where r.k_name = :nameRole",nativeQuery = true)
    Role findRolesByName(@Param("nameRole") String nameRole);
}
