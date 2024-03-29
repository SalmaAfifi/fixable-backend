package com.thesis.fixable.customer.service;

import com.thesis.fixable.auth.user.Role;
import com.thesis.fixable.auth.user.UserEntity;
import com.thesis.fixable.customer.CustomerDTO;
import com.thesis.fixable.customer.CustomerEntity;

public class TestCustomerUtil {

    static final CustomerDTO DTO = new CustomerDTO(
            "email@email.com",
            "password",
            "first name",
            "last name",
            "url/avatar",
            "+36 22 2222"
    );

    static final String DTO_JSON = String.join(System.lineSeparator(),
            "{",
            "\"email\": \"email@email.com\",",
            "\"password\": \"password\",\n",
            "\"firstName\": \"first name\",",
            "\"lastName\": \"last name\",",
            "\"avatar\": \"url/avatar\",",
            "\"phoneNumber\": \"+36 22 2222\"",
            "}"
    );

    static final CustomerEntity ENTITY = new CustomerEntity(
            "first name",
            "last name",
            new UserEntity(
                    "email@email.com",
                    "password",
                    Role.CUSTOMER
            ),
            "+36 22 2222",
            "url/avatar"
    );
}
