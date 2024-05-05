package com.tfm.tfmcore.infrastructure.mongodb.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tfm.tfmcore.TestConfig;
import com.tfm.tfmcore.domain.exceptions.ConflictException;
import com.tfm.tfmcore.domain.exceptions.NotFoundException;
import com.tfm.tfmcore.domain.models.user.User;
import com.tfm.tfmcore.domain.persistence.user.UserPersistence;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class UserPersistenceIT {

    @Autowired
    private UserPersistence userPersistence;

    @Test
    void testReadNotFound() {
        assertThrows(NotFoundException.class, () -> this.userPersistence.read("not_found"));
    }

    @Test
    void testUpdateNotFound() {
        assertThrows(NotFoundException.class, () -> this.userPersistence.update("not_found", null));
    }

    @Test
    void testCreateReadDelete() {
        User user = new User("user_test", "user_test@gmail.com", "user_test");
        this.userPersistence.create(user);
        assertEquals(user, this.userPersistence.read("user_test"));
        this.userPersistence.delete("user_test");
    }

    @Test
    void testCreateConflict() {
        User user = new User("user_test", "user_test@gmail.com", "user_test");
        this.userPersistence.create(user);
        User user2 = new User("user_test", "conflict", "conflict");
        assertThrows(ConflictException.class, () -> this.userPersistence.create(user2));
        this.userPersistence.delete("user_test");
    }

    @Test
    void testUpdateConflict() {
        User user = new User("user_test", "user_test@gmail.com", "user_test");
        this.userPersistence.create(user);
        User user2 = new User("user_test2", "user_test2@gmail.com", "user_test2");
        this.userPersistence.create(user2);
        User user_conflict = new User("user_test", "conflict@gmail.com", "conflict");
        assertThrows(ConflictException.class, ()-> this.userPersistence.update("user_test2", user_conflict));
        this.userPersistence.delete("user_test");
        this.userPersistence.delete("user_test2");
    }

    @Test
    void testUpdate() {
        User user = new User("user_test", "user_test@gmail.com", "user_test");
        this.userPersistence.create(user);
        User user_update = new User("user_update", "user_update@gmail.com", "user_update");
        this.userPersistence.update("user_test", user_update);
        assertEquals(user_update, this.userPersistence.read("user_update"));
        this.userPersistence.delete("user_update");
    }

}
