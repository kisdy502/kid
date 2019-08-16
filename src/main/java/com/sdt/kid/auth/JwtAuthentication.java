package com.sdt.kid.auth;

import com.sdt.kid.bean.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import static java.util.Collections.emptyList;


public class JwtAuthentication implements Authentication {

    private String token;
    private User user;
    private Map<String, Object> details;

    public JwtAuthentication(User user, String token, Map<String, Object> details) {
        this.token = token;
        this.user = user;
        this.details = details;
    }

    public Optional<User> user() {
        return Optional.ofNullable(user);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return emptyList();
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getDetails() {
        return details;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return user != null && user.getName() != null && user.getMobile() != null;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        if (!isAuthenticated()) {
            user = null;
        }
    }

    @Override
    public String getName() {
        return user.getName();
    }

    @Override
    public String toString() {
        return token;
    }
}
