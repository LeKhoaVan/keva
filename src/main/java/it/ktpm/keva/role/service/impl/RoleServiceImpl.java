package it.ktpm.keva.role.service.impl;

import it.ktpm.keva.common.util.KevaMapper;
import it.ktpm.keva.role.dto.RoleDTO;
import it.ktpm.keva.role.model.Role;
import it.ktpm.keva.role.repository.RoleRepository;
import it.ktpm.keva.role.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final KevaMapper kevaMapper;

    public RoleServiceImpl(RoleRepository roleRepository, KevaMapper kevaMapper) {
        this.roleRepository = roleRepository;
        this.kevaMapper = kevaMapper;
    }

    @Override
    public JpaRepository<Role, UUID> getRepository() {
        return this.roleRepository;
    }

    @Override
    public ModelMapper getMapper() {
        return this.kevaMapper;
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

    @Override
    public RoleDTO saveRole(RoleDTO roleDTO) {
        Role role = kevaMapper.map(roleDTO,Role.class);
        Role roleSave = roleRepository.save(role);
        RoleDTO dto = kevaMapper.map(roleSave,RoleDTO.class);
        return dto;
    }
}
