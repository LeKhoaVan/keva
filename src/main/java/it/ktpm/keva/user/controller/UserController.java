package it.ktpm.keva.user.controller;

import it.ktpm.keva.common.util.ResponseUtils;
import it.ktpm.keva.user.dto.UserDTO;
import it.ktpm.keva.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/findAll")
    public Object findAll(){
        return ResponseUtils.get(userService.findAllDto(UserDTO.class),
                HttpStatus.OK);
    }

    @PostMapping("/save")
    public Object saveUser(@RequestBody UserDTO userDTO){
        return ResponseUtils.get(userService.createUser(userDTO),
                HttpStatus.CREATED);
    }
}
