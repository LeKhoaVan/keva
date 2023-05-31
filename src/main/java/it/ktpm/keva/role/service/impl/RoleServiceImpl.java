package it.ktpm.keva.role.service.impl;

import it.ktpm.keva.role.model.Role;
import it.ktpm.keva.role.repository.RoleRepository;
import it.ktpm.keva.role.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Role role, String code) {
        Role curRole = roleRepository.findByCode(code);
        curRole.setName(role.getName());
        curRole.setDescription(role.getDescription());
        return roleRepository.save(curRole);
    }

    @Override
    public Role deleteRole(String code) {
        return roleRepository.deleteByCode(code);
    }

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findRolesByName(name);
    }
}
