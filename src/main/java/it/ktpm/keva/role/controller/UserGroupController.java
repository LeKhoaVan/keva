package it.ktpm.keva.role.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import it.ktpm.keva.common.util.ResponseUtils;
import it.ktpm.keva.role.dto.UserGroupDTO;
import it.ktpm.keva.role.service.UserGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user_group")
public class UserGroupController {
    private final UserGroupService userGroupService;

    public UserGroupController(UserGroupService userGroupService) {
        this.userGroupService = userGroupService;
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/findAll")
    public Object findAll(){
        return ResponseUtils.get(userGroupService.findAllDto(UserGroupDTO.class),
                HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/save")
    public Object saveUserGroup(@RequestBody UserGroupDTO userGroupDTO){
        return ResponseUtils.get(userGroupService.addUserGroup(userGroupDTO),
                HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("{usergroup-id}/add-user-to-usergroup")
    public Object addUserToUserGroup(@RequestBody List<UUID> ids,
                               @PathVariable("usergroup-id") UUID idGroup){
        return ResponseUtils.get(userGroupService.addUserToUserGroup(ids,idGroup),HttpStatus.CREATED);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/{usergroup-id}/add-role to-usergroup")
    public Object addRoleToUserGroup(@RequestBody List<UUID> ids,
                                     @PathVariable("usergroup-id") UUID idGroup){
        return ResponseUtils.get(userGroupService.addRoleToUserGroup(ids,idGroup),HttpStatus.OK);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/include-users")
    public Object findAllUserGroupIncludeUser(){
        return ResponseUtils.get(userGroupService.findAllDTOIncludeUser(),
                HttpStatus.OK);
    }
}
