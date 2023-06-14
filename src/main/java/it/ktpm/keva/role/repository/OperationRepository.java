package it.ktpm.keva.role.repository;

import it.ktpm.keva.role.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface OperationRepository extends JpaRepository<Operation, UUID> {

    @Query("select o from Operation o " +
            "left join o.roles r " +
            "left join r.userGroups g " +
            "left join g.users u where u.userName = :userName and o.name = :name")
    List<Operation> findAllByNameAndUserName( @Param("name") String operationName, @Param("userName") String userName);
}
