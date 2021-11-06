package com.voitekhov.springsecurityproject.security.token;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class TokenManager {

    Set<String> tokens = new HashSet<>();

    public void addToken(String token) {
        tokens.add(token);
    }

    public Boolean containToken(String token) {
        return tokens.contains(token);
    }
}
