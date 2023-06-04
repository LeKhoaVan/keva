package it.ktpm.keva.role.repository;

import it.ktpm.keva.role.dto.UserGroupWithUserDTO;
import it.ktpm.keva.role.model.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserGroupRepository extends JpaRepository<UserGroup, UUID> {
    @Query("select ug from UserGroup ug left join fetch ug.users")
    List<UserGroup> findAllWithUser();
}
