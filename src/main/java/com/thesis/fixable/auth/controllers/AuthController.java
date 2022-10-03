package com.thesis.fixable.auth.controllers;

import com.thesis.fixable.auth.dto.AuthRequest;
import com.thesis.fixable.auth.dto.TokenResponse;
import com.thesis.fixable.auth.services.AuthUserDetailsService;
import com.thesis.fixable.auth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AuthUserDetailsService authUserDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        );
        UserDetails userDetails = authUserDetailsService.loadUserByUsername(authRequest.getUsername());
        System.out.println(userDetails);

        String token = jwtUtil.generateToken(userDetails);

        TokenResponse response = new TokenResponse(token);

        return ResponseEntity.ok(response);
    }
}
