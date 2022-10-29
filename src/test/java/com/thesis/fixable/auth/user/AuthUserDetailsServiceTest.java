package com.thesis.fixable.auth.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesis.fixable.customer.CustomerService;
import com.thesis.fixable.exceptionshandling.exceptions.EmailAlreadyExistException;
import com.thesis.fixable.technician.TechnicianEntity;
import com.thesis.fixable.technician.TechnicianService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
public class AuthUserDetailsServiceTest {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    AuthUserDetailsService userServices;

    @Mock
    Authentication mockedAuth;

    @MockBean
    TechnicianService mockedTechnicianService;

    @MockBean
    CustomerService mockedCustomerService;

    @Test
    void testCreateUser() {
        UserEntity userEntity = userServices.addUser(TestUserUtil.USER_ENTITY);
        assertEquals(TestUserUtil.USER_ENTITY, userEntity);
    }

    @Test
    void testGetUser() {
        userServices.addUser(TestUserUtil.USER_ENTITY);
        UserEntity actual = userServices.loadUserByEmail("salma@gmail.com");
        assertEquals(TestUserUtil.USER_ENTITY, actual);
    }

    @Test
    void testCreateUserFromJson() throws JsonProcessingException {
        UserEntity createdFromJson = OBJECT_MAPPER.readValue(TestUserUtil.USER_JSON, UserEntity.class);
        UserEntity actual = userServices.addUser(createdFromJson);
        assertEquals(TestUserUtil.USER_ENTITY, actual);
    }

    @Test
    void testSerializeUserToJson() throws JsonProcessingException {
        UserEntity userEntity = userServices.addUser(TestUserUtil.USER_ENTITY);
        String serializedJson = OBJECT_MAPPER.writeValueAsString(userEntity);
        UserEntity actual = OBJECT_MAPPER.readValue(serializedJson, UserEntity.class);
        assertEquals(TestUserUtil.USER_ENTITY, actual);
    }

    @Test
    void testAddNewUserWithExistingEmail() {
        userServices.addUser(TestUserUtil.USER_ENTITY);
        assertThrows(EmailAlreadyExistException.class,
                () -> userServices.addUser(new UserEntity(
                        TestUserUtil.USER_ENTITY.getEmail(),
                        "differentPassword",
                        Role.TECHNICIAN
                ))
        );
    }

    @Test
    void testGetRoleByAuthentication() {
        when(mockedAuth.getPrincipal()).thenReturn(new UserEntity("email@email.com", "password", Role.TECHNICIAN));
        Role actual = userServices.getRoleByAuthentication(mockedAuth);
        assertEquals(Role.TECHNICIAN, actual);
    }

    @Test
    void testGetIdByAuthentication() {
        TechnicianEntity technicianUser = new TechnicianEntity();
        when(mockedAuth.getPrincipal()).thenReturn(new UserEntity("email@email.com", "password", Role.TECHNICIAN));
        when(mockedTechnicianService.getTechnicianByEmail("email@email.com")).thenReturn(technicianUser);

        Long actual = userServices.getIdByAuthentication(mockedAuth);
        assertEquals(technicianUser.getId(), actual);
    }
}
