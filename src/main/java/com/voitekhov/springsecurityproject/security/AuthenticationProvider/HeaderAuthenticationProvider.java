package com.voitekhov.springsecurityproject.security.AuthenticationProvider;

import com.voitekhov.springsecurityproject.security.authentication.UsernamePasswordAuthentication;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class HeaderAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String header = String.valueOf(authentication.getPrincipal());
        if (header.equals("123")) {
            return new UsernamePasswordAuthenticationToken(null, null, null);
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthentication.class.equals(authentication);
    }
}
