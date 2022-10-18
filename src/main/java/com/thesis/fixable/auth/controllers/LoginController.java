package com.thesis.fixable.auth.controllers;

import com.thesis.fixable.auth.dto.AuthRequest;
import com.thesis.fixable.auth.dto.LoginResponse;
import com.thesis.fixable.auth.jwt.JwtUtil;
import com.thesis.fixable.auth.user.AuthUserDetailsService;
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

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()
                )
        );

        LOGGER.info(authentication.toString());

        String token = jwtUtil.generateToken(authentication);

        Long id = authUserDetailsService.getIdByAuthentication(authentication);

        LoginResponse response = new LoginResponse(token, id);

        return ResponseEntity.ok(response);
    }
}
