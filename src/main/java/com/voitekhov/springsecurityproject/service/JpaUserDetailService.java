package com.voitekhov.springsecurityproject.service;

import com.voitekhov.springsecurityproject.model.SecurityUser;
import com.voitekhov.springsecurityproject.model.User;
import com.voitekhov.springsecurityproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class JpaUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Cann`t find user"));
        return new SecurityUser(user);
    }
}
