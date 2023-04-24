package com.example.ridewithme.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder authentication)throws Exception{
        authentication.userDetailsService(myUserDetailsService);

    }
    @Override
    protected void configure(HttpSecurity security)throws Exception{
        security.authorizeRequests()
                .antMatchers("/**/**/admin").hasRole("ADMIN")
                .antMatchers("/**/**/user").hasRole("USER");
    }

}
