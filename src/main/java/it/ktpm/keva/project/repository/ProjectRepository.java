package it.ktpm.keva.project.repository;

import it.ktpm.keva.project.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {

    @Query("select p from Project p where p.code = :code")
    Project handleFindByCode(@Param("code") String code);

    @Modifying(clearAutomatically = true)
    @Query("update Project p set p.status = :content where p.code = :code")
    void changeStatusProject(@Param("code") String code, @Param("content") Project.Status content);

    @Query("select p from Project p left join Work w on p.id = w.project.id where p.code = :codeProject and w.status ='ACTIVE'")
    Project checkCompleteWork(@Param("codeProject") String codeProject);
}
