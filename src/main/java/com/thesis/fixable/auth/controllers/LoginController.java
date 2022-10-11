package com.thesis.fixable.auth.controllers;

import com.thesis.fixable.auth.dto.AuthRequest;
import com.thesis.fixable.auth.dto.TokenResponse;
import com.thesis.fixable.auth.jwt.JwtUtil;
import com.thesis.fixable.auth.user.AuthUserDetailsService;
import com.thesis.fixable.auth.user.Role;
import com.thesis.fixable.auth.user.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AuthUserDetailsService authUserDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    private static final UserEntity USER = new UserEntity("email@email.com", "password", Role.TECHNICIAN);

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody @Valid AuthRequest authRequest) {
        authUserDetailsService.addUser(USER);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()
                )
        );

        LOGGER.info(authentication.toString());

        String token = jwtUtil.generateToken(authentication);

        TokenResponse response = new TokenResponse(token);

        return ResponseEntity.ok(response);
    }
}
