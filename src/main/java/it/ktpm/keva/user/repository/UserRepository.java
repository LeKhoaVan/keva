package it.ktpm.keva.user.repository;

import it.ktpm.keva.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUserName(String username);

    @Query("select u from User u where u.email = :email")
    User findByEmail(@Param("email") String email);
}

