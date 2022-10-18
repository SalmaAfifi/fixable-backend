package com.thesis.fixable.auth.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesis.fixable.exceptionshandling.exceptions.EmailAlreadyExistException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class AuthUserDetailsServiceTest {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    AuthUserDetailsService userServices;

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
}
