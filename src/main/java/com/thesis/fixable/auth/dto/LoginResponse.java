package com.thesis.fixable.auth.dto;

public class LoginResponse {
    private String token;

    private Long id;

    public LoginResponse() {
    }

    public LoginResponse(String token, Long id) {
        this.token = token;
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public Long getId() {
        return id;
    }
}
