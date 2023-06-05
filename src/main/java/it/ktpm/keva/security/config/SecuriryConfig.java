package it.ktpm.keva.security.config;


import it.ktpm.keva.security.service.UserDetaiServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecuriryConfig {
    private final UserDetaiServiceImpl userDetaiService;

    public SecuriryConfig(UserDetaiServiceImpl userDetaiService) {
        this.userDetaiService = userDetaiService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetaiService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //cross origin cho phep goi qua lai voi nhau
        http.csrf(csrf -> csrf.disable());

        //DISABLE SESSION -> STAELESS
        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        //Api authentication
        http.authorizeHttpRequests(request -> request
                .requestMatchers("/roles/**")
                    .authenticated()
                .requestMatchers("/login")
                    .permitAll()
                .anyRequest()
//                    .authenticated());
                    .permitAll());

        return http.build();
    }


}
