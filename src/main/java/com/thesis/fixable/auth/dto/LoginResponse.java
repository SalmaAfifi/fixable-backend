package com.thesis.fixable.auth.dto;

import com.thesis.fixable.auth.user.Role;

public class LoginResponse {
    private String token;

    private Long id;

    private Role role;

    public LoginResponse() {
    }

    public LoginResponse(String token, Long id, Role role) {
        this.token = token;
        this.id = id;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public Long getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }
}
