package it.ktpm.keva.user.service.impl;

import it.ktpm.keva.common.util.KevaMapper;
import it.ktpm.keva.user.dto.UserDTO;
import it.ktpm.keva.user.model.User;
import it.ktpm.keva.user.repository.UserRepository;
import it.ktpm.keva.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final KevaMapper mapper;

    public UserServiceImpl(UserRepository userRepository, KevaMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public JpaRepository<User, UUID> getRepository() {
        return this.userRepository;
    }

    @Override
    public ModelMapper getMapper() {
        return this.mapper;
    }


    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        User userCur = mapper.map(userDTO,User.class);
        User userSave = userRepository.save(userCur);
        UserDTO dto = mapper.map(userSave, UserDTO.class);
        return dto;
    }

    @Override
    public List<User> findAll(List<UUID> ids) {
        return userRepository.findAllById(ids);
    }
}
