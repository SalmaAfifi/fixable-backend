package com.thesis.fixable.customer;

import com.thesis.fixable.auth.user.Role;
import com.thesis.fixable.auth.user.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    CustomerRepository repo;

    @Test
    void testUserExistByEmail() {
        CustomerEntity customerEntity = new CustomerEntity(
                "first name",
                "last name",
                new UserEntity(
                        "email@email.com",
                        "password",
                        Role.CUSTOMER
                ),
                "+36 22 2255",
                "avatarUri"
        );

        entityManager.persistAndFlush(customerEntity);

        assertTrue(repo.existsByUser_EmailIgnoreCase("email@email.com"));
    }

    @Test
    void testUserExistByEmail_WhenEmailDoesNotExist() {
        CustomerEntity customerEntity = new CustomerEntity(
                "first name",
                "last name",
                new UserEntity(
                        "email@email.com",
                        "password",
                        Role.CUSTOMER
                ),
                "+36 22 2255",
                "avatarUri"
        );

        entityManager.persistAndFlush(customerEntity);

        assertFalse(repo.existsByUser_EmailIgnoreCase("notExistingEmail@email.com"));
    }

    @Test
    void testFindUserByEmail() {
        CustomerEntity expected = new CustomerEntity(
                "first name",
                "last name",
                new UserEntity(
                        "email@email.com",
                        "password",
                        Role.CUSTOMER
                ),
                "+36 22 2255",
                "avatarUri"
        );

        entityManager.persistAndFlush(expected);

        Optional<CustomerEntity> actual = repo.findByUser_EmailIgnoreCase("email@email.com");

        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    void testFindUserByEmail_WhenEmailDoesNotExist() {
        CustomerEntity expected = new CustomerEntity(
                "first name",
                "last name",
                new UserEntity(
                        "email@email.com",
                        "password",
                        Role.CUSTOMER
                ),
                "+36 22 2255",
                "avatarUri"
        );

        entityManager.persistAndFlush(expected);

        Optional<CustomerEntity> actual = repo.findByUser_EmailIgnoreCase("nonExistingEmail@email.com");

        assertFalse(actual.isPresent());
    }
}