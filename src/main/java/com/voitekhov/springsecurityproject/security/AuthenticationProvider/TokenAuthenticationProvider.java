package com.voitekhov.springsecurityproject.security.AuthenticationProvider;

import com.voitekhov.springsecurityproject.security.authentication.TokenAuthentication;
import com.voitekhov.springsecurityproject.security.token.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private TokenManager tokenManager;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = authentication.getName();
        if (token != null) {
            if (tokenManager.containToken(token)) {
                return new TokenAuthentication(token, null, List.of(() -> "read"));
            }
            throw new AuthenticationCredentialsNotFoundException("Bad token");
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.equals(authentication);
    }
}
