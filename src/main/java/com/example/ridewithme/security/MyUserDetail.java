package com.example.ridewithme.security;

import com.example.ridewithme.models.User;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetail implements UserDetails {

    private String  email;
    private String  password;
    private  boolean active;


    public MyUserDetail(User user  ){
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.active=user.isActive();
        this.authorityList= Arrays.stream(user.getRoles().split("  , "))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
    private List<GrantedAuthority> authorityList;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}