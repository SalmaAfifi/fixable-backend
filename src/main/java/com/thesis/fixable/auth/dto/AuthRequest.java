package com.thesis.fixable.auth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@JsonInclude
public class AuthRequest {

    @Email
    @NotNull
    @NotEmpty
    private String email;

    @NotEmpty
    @NotEmpty
    private String password;

    public AuthRequest() {
    }

    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "AuthRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
