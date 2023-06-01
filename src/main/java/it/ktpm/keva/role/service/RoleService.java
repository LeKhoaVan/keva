package it.ktpm.keva.role.service;

import it.ktpm.keva.common.service.GenericService;
import it.ktpm.keva.role.dto.RoleDTO;
import it.ktpm.keva.role.model.Role;

import java.util.List;
import java.util.UUID;

public interface RoleService extends GenericService<Role, UUID, RoleDTO> {

    Role updateRole(Role role, String code);

    Role deleteRole(String code);
    Role findRoleByName(String name);
}
