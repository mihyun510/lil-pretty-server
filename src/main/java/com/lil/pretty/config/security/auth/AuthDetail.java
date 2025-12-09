package com.lil.pretty.config.security.auth;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lil.pretty.domain.user.model.User;


public class AuthDetail implements UserDetails {
    private final User user;

    public AuthDetail(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // DB에 저장된 role 사용 (ex: "ADMIN", "USER")
    	return List.of(() -> user.getUsRole());
    }

    @Override
    public String getPassword() {
        return user.getUsPw();
    }

    @Override
    public String getUsername() {
        return user.getUsId();
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
