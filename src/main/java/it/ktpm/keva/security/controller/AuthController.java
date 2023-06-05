package it.ktpm.keva.security.controller;

import it.ktpm.keva.common.util.ResponseUtils;
import it.ktpm.keva.security.dto.LoginDTO;
import it.ktpm.keva.security.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public Object login(@RequestBody @Valid LoginDTO loginDTO){
        return ResponseUtils.get(authService.login(loginDTO),
                HttpStatus.OK);
    }
}
