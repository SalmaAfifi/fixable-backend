package com.thesis.fixable.auth.user;

public class TestUserUtil {
    public static final UserEntity USER_ENTITY = new UserEntity("salma@gmail.com", "password", Role.CUSTOMER);

    public static final String USER_JSON = String.join(System.lineSeparator(),
            "{",
            "\"email\": \"salma@gmail.com\",",
            "\"password\": \"password\",",
            "\"role\": \"CUSTOMER\"",
            "}"
    );
}
