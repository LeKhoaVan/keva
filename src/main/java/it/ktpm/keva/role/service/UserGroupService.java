package it.ktpm.keva.role.service;

import it.ktpm.keva.common.service.GenericService;
import it.ktpm.keva.role.dto.UserGroupDTO;
import it.ktpm.keva.role.dto.UserGroupWithRoleDTO;
import it.ktpm.keva.role.dto.UserGroupWithUserDTO;
import it.ktpm.keva.role.model.UserGroup;

import java.util.List;
import java.util.UUID;

public interface UserGroupService extends GenericService<UserGroup, UUID, UserGroupDTO> {
    UserGroupDTO addUserGroup(UserGroupDTO userGroupDTO);
    UserGroupWithUserDTO addUserToUserGroup(List<UUID> ids, UUID idGroup);


    List<UserGroupWithUserDTO> findAllDTOIncludeUser();

    UserGroupWithRoleDTO addRoleToUserGroup(List<UUID> ids, UUID idGroup);
}
