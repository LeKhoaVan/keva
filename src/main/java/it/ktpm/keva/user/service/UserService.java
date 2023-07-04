package it.ktpm.keva.user.service;

import it.ktpm.keva.common.service.GenericService;
import it.ktpm.keva.user.dto.UserDTO;
import it.ktpm.keva.user.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService extends GenericService<User, UUID, UserDTO> {
    UserDTO saveUser(UserDTO userDTO);
    UserDTO createUser(UserDTO userDTO);
    List<User> findAll(List<UUID> ids);

    Optional<User> findByUserName(String value);

    User findByEmail(String email);

    User findById(UUID user);
}
