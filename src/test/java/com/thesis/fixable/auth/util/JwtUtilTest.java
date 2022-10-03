package com.thesis.fixable.auth.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class JwtUtilTest {

    @Mock
    User mockedUserDetails;

    @Autowired
    JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        Mockito.when(mockedUserDetails.getUsername()).thenReturn("salma");
    }

    @Test
    void jwtGenerateTest() {
        System.out.println(jwtUtil.generateToken(mockedUserDetails));
    }
}