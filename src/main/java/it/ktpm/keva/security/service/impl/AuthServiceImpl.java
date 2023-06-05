package it.ktpm.keva.security.service.impl;

import it.ktpm.keva.common.exception.KevaBusinessException;
import it.ktpm.keva.security.dto.LoginDTO;
import it.ktpm.keva.security.jwt.JwtUtils;
import it.ktpm.keva.security.service.AuthService;
import it.ktpm.keva.user.model.User;
import it.ktpm.keva.user.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthServiceImpl(UserService userService, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public String login(LoginDTO loginDTO) {
        User user = userService.findByUserName(loginDTO.getUserName())
                .orElseThrow(
                        ()-> new KevaBusinessException("UserName incorrect!!!")
                );
        if(passwordEncoder.matches(loginDTO.getPassword(),user.getPassword())){
            return jwtUtils.generateJwt(loginDTO.getUserName());
        }
        throw new KevaBusinessException("Password incorrect!!!");
    }
}
