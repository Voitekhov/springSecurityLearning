package com.voitekhov.springsecurityproject.security.AuthenticationProvider;

import com.voitekhov.springsecurityproject.model.Opt;
import com.voitekhov.springsecurityproject.repository.OptRepository;
import com.voitekhov.springsecurityproject.repository.UserRepository;
import com.voitekhov.springsecurityproject.security.authentication.OptAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OptAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    OptRepository optRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        Optional<Opt> opt = optRepository.getOptByUsername(userRepository.findByUsername(username).get().getId());
        if (opt.isPresent()) {
            return new OptAuthentication(opt.get().getUser().getUsername(),
                    opt.get().getOpt(), List.of(() -> "read"));
        }
        throw new AuthenticationCredentialsNotFoundException("Not found");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OptAuthentication.class.equals(authentication);
    }
}
