package com.voitekhov.springsecurityproject.security.filter;

import com.voitekhov.springsecurityproject.model.Opt;
import com.voitekhov.springsecurityproject.model.OptUtils;
import com.voitekhov.springsecurityproject.repository.OptRepository;
import com.voitekhov.springsecurityproject.repository.UserRepository;
import com.voitekhov.springsecurityproject.security.authentication.OptAuthentication;
import com.voitekhov.springsecurityproject.security.authentication.UsernamePasswordAuthentication;
import com.voitekhov.springsecurityproject.security.token.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


public class CustomFilter extends OncePerRequestFilter {

    @Autowired
    OptRepository optRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenManager tokenManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String username = String.valueOf(request.getHeader("username"));
        String password = String.valueOf(request.getHeader("password"));
        String opt = String.valueOf(request.getHeader("opt"));
        String auth = String.valueOf(request.getHeader("Authentication"));
        
        if (!auth.equals("null")) {
            filterChain.doFilter(request, response);
            return;
        }
        Authentication authentication = null;
        if (Objects.equals(opt, "null")) {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthentication(username, password, null));
            if (authentication.isAuthenticated()) {
                Opt optEntity = new Opt(userRepository.findByUsername(username).get(),
                        OptUtils.generateOpt());
                optRepository.save(optEntity);
            }
        } else {
            authentication = authenticationManager.authenticate(new OptAuthentication(username, opt));
            if (authentication.isAuthenticated()) {
                String uuid = UUID.randomUUID().toString();
                tokenManager.addToken(uuid);
                response.setHeader("Authentication", uuid);
            } else {
                response.setStatus(502);
            }

        }


    }
}
