package it.ktpm.keva.role.controller;

import it.ktpm.keva.role.model.Role;
import it.ktpm.keva.role.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/getAll")
    public Object getAll(){
        List<Role> roles = roleService.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @PostMapping("save")
    public Object saveRole(@RequestBody Role role){
        roleService.addRole(role);
        return new ResponseEntity<>(role,HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public Object updateRole(@RequestBody Role role){
        roleService.updateRole(role, role.getCode());
        return new ResponseEntity<>(role,HttpStatus.OK);
    }
}
