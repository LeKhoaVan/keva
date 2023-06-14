package it.ktpm.keva.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtUtils.getToken(request);

        if(jwtUtils.validateJwt(token)){
            String userName = jwtUtils.getUserName(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

            //add vao SecurityContext de quan ly tai khoan
            SecurityContextHolder
                    .getContext()
                    .setAuthentication(
                           new UsernamePasswordAuthenticationToken(
                                   userName,
                                   null,
                                   userDetails.getAuthorities()
                           )
                    );
        }


        filterChain.doFilter(request,response);
    }
}
