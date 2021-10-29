package com.voitekhov.springsecurityproject.configuration;

import com.voitekhov.springsecurityproject.filter.CustomFilter;
import com.voitekhov.springsecurityproject.service.AuthenticationProvider.CustomAuthenticationProvider;
import com.voitekhov.springsecurityproject.service.AuthenticationProvider.HeaderAuthenticationProvider;
import com.voitekhov.springsecurityproject.service.UserDetail.JpaUserDetailService;
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
    CustomFilter customFilter;

    @Autowired
    HeaderAuthenticationProvider headerAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAt(customFilter, BasicAuthenticationFilter.class);

        http.authorizeRequests()
                .anyRequest()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(headerAuthenticationProvider);
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
}
