package com.thesis.fixable.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesis.fixable.auth.dto.AuthRequest;
import com.thesis.fixable.auth.user.AuthUserDetailsService;
import com.thesis.fixable.auth.user.Role;
import com.thesis.fixable.auth.user.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    private static final UserEntity USER = new UserEntity("salma@gmail.com", "password", Role.TECHNICIAN);

    @MockBean
    AuthUserDetailsService userDetailsService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testBeansLoad() {
        Assertions.assertNotNull(userDetailsService);
        Assertions.assertNotNull(mockMvc);
    }

    @Test
    public void testLogin_WhenCredentialsMatch() throws Exception {
        Mockito.when(userDetailsService.loadUserByUsername("salma@gmail.com")).thenReturn(USER);
        AuthRequest request = new AuthRequest("salma@gmail.com", "password");
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").exists());
    }

    @Test
    public void testLogin_WhenCredentialsDoNOTMatch() throws Exception {
        Mockito.when(userDetailsService.loadUserByUsername("salma@gmail.com")).thenReturn(USER);
        AuthRequest request = new AuthRequest("salma@gmail.com", "wrongPassword");
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    public void testLogin_WhenUserNameIsNull() throws Exception {
        Mockito.when(userDetailsService.loadUserByUsername("salma@gmail.com")).thenReturn(USER);
        AuthRequest request = new AuthRequest(null, "wrongPassword");
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}