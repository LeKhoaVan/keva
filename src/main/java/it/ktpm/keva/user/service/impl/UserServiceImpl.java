package it.ktpm.keva.user.service.impl;

import it.ktpm.keva.common.util.KevaMapper;
import it.ktpm.keva.user.dto.UserDTO;
import it.ktpm.keva.user.model.User;
import it.ktpm.keva.user.repository.UserRepository;
import it.ktpm.keva.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final KevaMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, KevaMapper mapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
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
    public UserDTO createUser(UserDTO userDTO) {
        User userCur = mapper.map(userDTO,User.class);
        userCur.setPassword(passwordEncoder.encode(userCur.getPassword()));
        User userSave = userRepository.save(userCur);
        UserDTO dto = mapper.map(userSave, UserDTO.class);
        return dto;
    }

    @Override
    public List<User> findAll(List<UUID> ids) {
        return userRepository.findAllById(ids);
    }

    @Override
    public Optional<User> findByUserName(String value) {
        return userRepository.findByUserName(value);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(UUID user) {
        return userRepository.findById(user).orElse(null);
    }
}
