package it.ktpm.keva.role.service.impl;

import it.ktpm.keva.common.exception.KevaBusinessException;
import it.ktpm.keva.common.util.KevaMapper;
import it.ktpm.keva.role.dto.UserGroupDTO;
import it.ktpm.keva.role.dto.UserGroupWithRoleDTO;
import it.ktpm.keva.role.dto.UserGroupWithUserDTO;
import it.ktpm.keva.role.model.Role;
import it.ktpm.keva.role.model.UserGroup;
import it.ktpm.keva.role.repository.UserGroupRepository;
import it.ktpm.keva.role.service.RoleService;
import it.ktpm.keva.role.service.UserGroupService;
import it.ktpm.keva.user.model.User;
import it.ktpm.keva.user.service.UserService;
import jakarta.validation.ValidationException;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserGroupServiceImpl implements UserGroupService {
    private final UserGroupRepository userGroupRepository;
    private final UserService userService;
    private final RoleService roleService;
    private final KevaMapper mapper;

    public UserGroupServiceImpl(UserGroupRepository userGroupRepository, UserService userService, RoleService roleService, KevaMapper mapper) {
        this.userGroupRepository = userGroupRepository;
        this.userService = userService;
        this.roleService = roleService;
        this.mapper = mapper;
    }

    @Override
    public JpaRepository<UserGroup, UUID> getRepository() {
        return this.userGroupRepository;
    }

    @Override
    public ModelMapper getMapper() {
        return this.mapper;
    }

    @Override
    public UserGroupDTO addUserGroup(UserGroupDTO userGroupDTO) {
        UserGroup userGroup = mapper.map(userGroupDTO,UserGroup.class);
        UserGroup userGroupSave = userGroupRepository.save(userGroup);
        UserGroupDTO dto = mapper.map(userGroupSave,UserGroupDTO.class);
        return dto;
    }

    @Override
    public UserGroupWithUserDTO addUserToUserGroup(List<UUID> ids, UUID idGroup) {
        UserGroup userGroup = userGroupRepository.findById(idGroup).orElseThrow(
               () -> new ValidationException("User group not exist"));

        List<User> users = userService.findAll(ids);

        users.forEach(user -> userGroup.addUser(user));

        return mapper.map(userGroup, UserGroupWithUserDTO.class);
    }
    @Override
    public UserGroupWithRoleDTO addRoleToUserGroup(List<UUID> ids, UUID idGroup) {
        UserGroup userGroup = userGroupRepository.findById(idGroup).orElseThrow(
                () -> new KevaBusinessException("User Group not is exist")
        );
        List<Role> roles = roleService.findAllById(ids);

        roles.forEach(role -> userGroup.addRole(role));

        return mapper.map(userGroup, UserGroupWithRoleDTO.class);
    }

    @Override
    public List<UserGroupWithUserDTO> findAllDTOIncludeUser() {
        return userGroupRepository.findAllWithUser().stream().
                map(model -> mapper.map(model,UserGroupWithUserDTO.class))
                .collect(Collectors.toList());
    }


}
