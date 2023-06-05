package it.ktpm.keva.security.service;

import it.ktpm.keva.security.dto.LoginDTO;

public interface AuthService {
    String login(LoginDTO loginDTO);
}
