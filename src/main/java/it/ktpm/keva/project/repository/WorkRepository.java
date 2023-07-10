package it.ktpm.keva.project.repository;

import it.ktpm.keva.project.model.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface WorkRepository extends JpaRepository<Work, UUID> {
    @Query("select w from Work w where w.code = :code")
    Work findCode(@Param("code") String code);

    @Query("update Work w set w.status = :status where w.code = :code")
    Work changeStatus(@Param("code") String code, @Param("status") Work.Status status);
}
