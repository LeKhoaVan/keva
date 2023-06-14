package it.ktpm.keva.role.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.ktpm.keva.common.util.ResponseUtils;
import it.ktpm.keva.role.dto.RoleDTO;
import it.ktpm.keva.role.model.Role;
import it.ktpm.keva.role.service.OperationService;
import it.ktpm.keva.role.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;
    private OperationService operationService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/getAll")
    public Object getAll(){
        List<RoleDTO> rolesDto = roleService.findAllDto(RoleDTO.class);
        return ResponseUtils.get(rolesDto, HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/get-page")
    public Object getPage(@RequestParam("page") int page,
                          @RequestParam("size") int size){

        List<RoleDTO> rolesDto = roleService.findAllDto(
                RoleDTO.class, Pageable.ofSize(size).withPage(page));

        return ResponseUtils.get(rolesDto, HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("save")
    public Object saveRole(@RequestBody @Valid RoleDTO roleDTO){
        RoleDTO dto = roleService.saveRole(roleDTO);
        return ResponseUtils.get(dto,HttpStatus.CREATED);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/update")
    public Object updateRole(@RequestBody Role role){
        roleService.updateRole(role, role.getCode());
        return ResponseUtils.get(role,HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")

    @PostMapping("{role-id}/add-operation")
    public Object addOperation(@RequestBody List<UUID> ids,
                               @PathVariable("role-id") UUID idRole){
        return ResponseUtils.get(roleService.addOperation(idRole,ids),HttpStatus.CREATED);
    }
}
