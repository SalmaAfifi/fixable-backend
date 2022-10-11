package com.thesis.fixable.auth.user;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    CUSTOMER, TECHNICIAN;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
