package com.thesis.fixable.auth.user;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class UserRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    UserRepository repo;

    @Test
    void testFindByEmail() {
        UserEntity user = new UserEntity("email@email.com", "password", Role.TECHNICIAN);
        entityManager.persist(user);
        entityManager.flush();

        Optional<UserEntity> found = repo.findByEmail(user.getEmail());

        assertTrue(found.isPresent());
        assertEquals(user, found.get());
    }


    @Test
    void testFindByEmail_WhenEmailNotExist() {
        UserEntity user = new UserEntity("email@email.com", "password", Role.TECHNICIAN);
        entityManager.persist(user);
        entityManager.flush();

        Optional<UserEntity> found = repo.findByEmail("differentEmail@email.com");

        assertFalse(found.isPresent());
    }

    @Test
    void testEmailExist() {
        UserEntity user = new UserEntity("email@email.com", "password", Role.TECHNICIAN);
        entityManager.persist(user);
        entityManager.flush();

        boolean isExists = repo.existsByEmail("email@email.com");

        assertTrue(isExists);
    }

    @Test
    void testEmailExist_WhenEmailNotExist() {
        UserEntity user = new UserEntity("email@email.com", "password", Role.TECHNICIAN);
        entityManager.persist(user);
        entityManager.flush();

        boolean isExists = repo.existsByEmail("different@email.com");

        assertFalse(isExists);
    }

    @Test
    void testSavingUser_WhenEmailNotUnique() {
        UserEntity user = new UserEntity("email@email.com", "password", Role.TECHNICIAN);
        entityManager.persistAndFlush(user);

        assertThrows(PersistenceException.class, () -> entityManager.persistAndFlush(
                        new UserEntity("email@email.com",
                                "password",
                                Role.CUSTOMER)
                )
        );
    }

    @Test
    void testUpdatePassword() {
        UserEntity user = new UserEntity("email@email.com", "password", Role.TECHNICIAN);
        entityManager.persistAndFlush(user);

        repo.updatePassword(user.getId(), "newPassword");

        UserEntity updated = repo.findById(user.getId()).get();

        assertEquals("newPassword", updated.getPassword());
    }

    @Disabled
    @Test
    void testCreateUserWithInvalidEmail() {
        assertThrows(ConstraintViolationException.class,
                () -> entityManager.persistAndFlush(new UserEntity("invalidemail", "ValidPassword", Role.CUSTOMER))
        );
    }

    @Test
    @Disabled
    void testCreateUserWithInvalidPassword() {
        assertThrows(ConstraintViolationException.class,
                () -> entityManager.persistAndFlush(new UserEntity("email@email.com", "short", Role.CUSTOMER))
        );
    }
    //TODO move the disabled validation test to the rest controller
}