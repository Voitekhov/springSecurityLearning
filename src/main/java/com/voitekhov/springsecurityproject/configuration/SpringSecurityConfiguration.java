package com.voitekhov.springsecurityproject.configuration;

import com.voitekhov.springsecurityproject.security.AuthenticationProvider.OptAuthenticationProvider;
import com.voitekhov.springsecurityproject.security.AuthenticationProvider.TokenAuthenticationProvider;
import com.voitekhov.springsecurityproject.security.AuthenticationProvider.UsernamePasswordAuthenticationProvider;
import com.voitekhov.springsecurityproject.security.UserDetail.JpaUserDetailService;
import com.voitekhov.springsecurityproject.security.filter.CustomFilter;
import com.voitekhov.springsecurityproject.security.filter.TokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;

    @Autowired
    OptAuthenticationProvider optAuthenticationProvider;

    @Autowired
    TokenAuthenticationProvider tokenAuthenticationProvider;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAt(customFilter(), BasicAuthenticationFilter.class);
        http.addFilterAfter(tokenFilter(), CustomFilter.class);
        http.authorizeRequests()
                .anyRequest()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(usernamePasswordAuthenticationProvider)
                .authenticationProvider(optAuthenticationProvider)
                .authenticationProvider(tokenAuthenticationProvider);
    }


    @Bean
    UserDetailsService customUserDetailsService() {
        return new JpaUserDetailService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public CustomFilter customFilter() {
        return new CustomFilter();
    }

    @Bean
    public TokenFilter tokenFilter() {
        return new TokenFilter();
    }
}
