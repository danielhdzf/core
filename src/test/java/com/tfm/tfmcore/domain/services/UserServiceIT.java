package com.tfm.tfmcore.domain.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tfm.tfmcore.TestConfig;
import com.tfm.tfmcore.domain.exceptions.ConflictException;
import com.tfm.tfmcore.domain.exceptions.NotFoundException;
import com.tfm.tfmcore.domain.models.user.User;
import com.tfm.tfmcore.domain.services.user.UserService;

@TestConfig
class UserServiceIT {

    @Autowired
    private UserService userService;

    @Test
    void testReadNotFound() {
        assertThrows(NotFoundException.class, () -> this.userService.readByUsername("not_found"));
    }

    @Test
    void testUpdateNotFound() {
        assertThrows(NotFoundException.class, () -> this.userService.update("not_found", null));
    }

    @Test
    void testCreateReadDelete() {
        User user = new User("user_test", "user_test@gmail.com", "user_test");
        this.userService.create(user);
        assertEquals(user.getUsername(), this.userService.readByUsername("user_test").getUsername());
        this.userService.delete("user_test");
    }

    @Test
    void testCreateConflict() {
        User user = new User("user_test", "user_test@gmail.com", "user_test");
        this.userService.create(user);
        User user2 = new User("user_test", "conflict", "conflict");
        assertThrows(ConflictException.class, () -> this.userService.create(user2));
        this.userService.delete("user_test");
    }

    @Test
    void testUpdateConflict() {
        User user = new User("user_test", "user_test@gmail.com", "user_test");
        this.userService.create(user);
        User user2 = new User("user_test2", "user_test2@gmail.com", "user_test2");
        this.userService.create(user2);
        User user_conflict = new User("user_test", "conflict@gmail.com", "conflict");
        assertThrows(ConflictException.class, ()-> this.userService.update("user_test2", user_conflict));
        this.userService.delete("user_test");
        this.userService.delete("user_test2");
    }

    @Test
    void testUpdate() {
        User user = new User("user_test", "user_test@gmail.com", "user_test");
        this.userService.create(user);
        User user_update = new User("user_update", "user_update@gmail.com", "user_update");
        this.userService.update("user_test", user_update);
        assertEquals(user_update.getUsername(), this.userService.readByUsername("user_update").getUsername());
        this.userService.delete("user_update");
    }
}
