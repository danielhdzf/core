package com.tfm.tfmcore.infraestructure.mongodb.repositories;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfm.tfmcore.infraestructure.mongodb.entities.user.*;
import com.tfm.tfmcore.infraestructure.mongodb.repositories.user.*;

@Service
public class DatabaseSeeder {

    private final UserRepository userRepository;

    @Autowired
    public DatabaseSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.deleteAllAndSeed();
    }


    public void deleteAllAndSeed() {
        this.deleteAll();
        this.seed();
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }
 
    public void seed() {
        LogManager.getLogger(this.getClass()).warn("------- Initial Load Database -----------");
        UserEntity[] users = {
            UserEntity.builder().username("user1").email("user1@gmail.com")
            .password("user1").build(),
            UserEntity.builder().username("user2").email("user2@gmail.com")
            .password("user2").build(),
            UserEntity.builder().username("user3").email("user3@gmail.com")
            .password("user3").build(),
            UserEntity.builder().username("user4").email("user4@gmail.com")
            .password("user4").build(),
        };
        this.userRepository.saveAll(List.of(users));
    }

}
