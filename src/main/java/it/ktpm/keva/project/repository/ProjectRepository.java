package it.ktpm.keva.project.repository;

import it.ktpm.keva.project.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {

    @Query("select p from Project p where p.code = :ccode")
    Project handleFindByCode(@Param("ccode") String code);

    @Modifying(clearAutomatically = true)
    @Query("update Project p set p.status = 'TEMPORARY_BLOCKED' where p.code = :code")
    void temporaryBlocked(@Param("code") String code);
}
