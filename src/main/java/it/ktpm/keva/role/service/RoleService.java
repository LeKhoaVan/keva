package it.ktpm.keva.role.service;

import it.ktpm.keva.role.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();
    Role addRole(Role role);

    Role updateRole(Role role, String code);

    Role deleteRole(String code);
    Role findRoleByName(String name);
}
