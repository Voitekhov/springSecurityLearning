package com.voitekhov.springsecurityproject.security.authentication;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class OptAuthentication extends UsernamePasswordAuthentication{
    public OptAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public OptAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
