package com.tfm.tfmcore.infrastructure.mongodb.repositories;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tfm.tfmcore.TestConfig;
import com.tfm.tfmcore.domain.models.user.User;
import com.tfm.tfmcore.infraestructure.mongodb.repositories.user.UserRepository;

@TestConfig
class UserRepositoryIT {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUsername() {
        assertTrue(this.userRepository.findByUsername("user1").isPresent());
        User user = this.userRepository.findByUsername("user1").get().toUser();
        assertTrue(user.getUsername().equals("user1"));
        assertTrue(user.getEmail().equals("user1@gmail.com"));
    }

}
