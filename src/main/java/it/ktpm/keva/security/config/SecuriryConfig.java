package it.ktpm.keva.security.config;


import it.ktpm.keva.security.jwt.JwtAuthenticationFilter;
import it.ktpm.keva.security.service.UserDetaiServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecuriryConfig {
    private final UserDetaiServiceImpl userDetaiService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecuriryConfig(UserDetaiServiceImpl userDetaiService, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userDetaiService = userDetaiService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
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

        //JWT Filter
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


        //Api authentication
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/auth/login").permitAll()
                .requestMatchers("/user/save").permitAll()
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/v3/api-docs/**").permitAll()
                .requestMatchers("/operation/**").authenticated()
                .anyRequest().authenticated());

        return http.build();
    }


}
