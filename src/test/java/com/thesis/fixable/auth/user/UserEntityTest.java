package com.thesis.fixable.auth.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class UserEntityTest {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    AuthUserDetailsService userServices;

    @Test
    void testCreateUser() {
        UserEntity userEntity = userServices.addUser(TestUserUtil.USER_ENTITY);
        Assertions.assertEquals(TestUserUtil.USER_ENTITY, userEntity);
        System.out.println(userEntity);
    }

    @Test
    void testGetUser() {
        userServices.addUser(TestUserUtil.USER_ENTITY);
        UserEntity actual = userServices.loadUserByEmail("salma@gmail.com");
        Assertions.assertEquals(TestUserUtil.USER_ENTITY, actual);
    }

    @Test
    void testCreateUserFromJson() throws JsonProcessingException {
        UserEntity createdFromJson = OBJECT_MAPPER.readValue(TestUserUtil.USER_JSON, UserEntity.class);
        UserEntity actual = userServices.addUser(createdFromJson);
        Assertions.assertEquals(TestUserUtil.USER_ENTITY, actual);
    }

    @Test
    void testSerializeUserToJson() throws JsonProcessingException {
        UserEntity userEntity = userServices.addUser(TestUserUtil.USER_ENTITY);
        String serializedJson = OBJECT_MAPPER.writeValueAsString(userEntity);
        UserEntity actual = OBJECT_MAPPER.readValue(serializedJson, UserEntity.class);
        Assertions.assertEquals(TestUserUtil.USER_ENTITY, actual);
    }
}
