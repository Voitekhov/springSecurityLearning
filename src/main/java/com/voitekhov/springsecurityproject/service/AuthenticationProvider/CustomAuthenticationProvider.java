package com.voitekhov.springsecurityproject.service.AuthenticationProvider;

import com.voitekhov.springsecurityproject.model.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String password = String.valueOf(authentication.getCredentials());
        SecurityUser user = (SecurityUser) userDetailsService.loadUserByUsername(authentication.getName());
        if (user != null) {
            if (passwordEncoder.matches(user.getPassword(), password)) {
                return new UsernamePasswordAuthenticationToken(user, password,null);
            }
            throw new AuthenticationCredentialsNotFoundException("Bad credentials");
        }
        // it means that its provider does not fit to this type of authentication
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
