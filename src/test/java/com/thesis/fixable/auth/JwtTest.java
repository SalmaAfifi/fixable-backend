package com.thesis.fixable.auth;

import com.thesis.fixable.auth.jwt.JwtUtil;
import com.thesis.fixable.auth.user.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;
import java.util.List;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class JwtTest {

    @Mock
    Authentication mockedAuthentication;

    @Autowired
    JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        Collection<? extends GrantedAuthority> a = List.of(Role.CUSTOMER);
        Mockito.when(mockedAuthentication.getName()).thenReturn("salma@gmail.com");
        Mockito.doReturn(a).when(mockedAuthentication).getAuthorities();
    }

    @Test
    void jwtGenerateTest() {
        System.out.println("generated token:" + jwtUtil.generateToken(mockedAuthentication));
    }
}