package com.tfm.tfmcore.infraestructure.mongodb.repositories;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tfm.tfmcore.infraestructure.mongodb.entities.core.ScoreEntity;
import com.tfm.tfmcore.infraestructure.mongodb.entities.user.*;
import com.tfm.tfmcore.infraestructure.mongodb.repositories.user.*;

@Service
public class DatabaseSeeder {

    @Autowired
    private final UserRepository userRepository;

    
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
            .password(new BCryptPasswordEncoder().encode("user1")).build(),
            UserEntity.builder().username("user2").email("user2@gmail.com")
            .password(new BCryptPasswordEncoder().encode("user2")).build(),
            UserEntity.builder().username("user3").email("user3@gmail.com")
            .password(new BCryptPasswordEncoder().encode("user3")).build(),
            UserEntity.builder().username("user4").email("user4@gmail.com")
            .password(new BCryptPasswordEncoder().encode("user4")).build(),
        };
        this.userRepository.saveAll(List.of(users));

        ScoreEntity[] scores = {
            ScoreEntity.builder().username("user1").score(100).game("game1").build(),
            ScoreEntity.builder().username("user2").score(300).game("game1").build(),
        };
    }

}
