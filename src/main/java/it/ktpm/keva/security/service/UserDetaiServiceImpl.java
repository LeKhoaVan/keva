package it.ktpm.keva.security.service;

import it.ktpm.keva.common.exception.KevaBusinessException;
import it.ktpm.keva.user.model.User;
import it.ktpm.keva.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetaiServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    public UserDetaiServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username)
                .orElseThrow(
                    () -> new KevaBusinessException("User is not existed")
        );

        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                Collections.emptyList()
        );
    }
}
