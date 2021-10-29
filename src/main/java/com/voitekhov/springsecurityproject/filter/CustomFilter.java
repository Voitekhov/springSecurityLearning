package com.voitekhov.springsecurityproject.filter;

import com.voitekhov.springsecurityproject.model.CustomAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomFilter extends OncePerRequestFilter {
    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorisationHeader = request.getHeader("Authorization");
        Authentication authentication = authenticationManager.authenticate(new CustomAuthentication(authorisationHeader, null));
        if (authentication.isAuthenticated()) {
            filterChain.doFilter(request, response);
        }
    }
}
