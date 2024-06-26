package com.tfm.tfmcore.infraestructure.mongodb.repositories;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tfm.tfmcore.infraestructure.mongodb.entities.core.ScoreEntity;
import com.tfm.tfmcore.infraestructure.mongodb.entities.core.StatsEntity;
import com.tfm.tfmcore.infraestructure.mongodb.entities.user.*;
import com.tfm.tfmcore.infraestructure.mongodb.repositories.core.ScoreRepository;
import com.tfm.tfmcore.infraestructure.mongodb.repositories.core.StatsRepository;
import com.tfm.tfmcore.infraestructure.mongodb.repositories.user.*;

@Service
public class DatabaseSeeder {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final ScoreRepository scoreRepository;

    @Autowired
    private final StatsRepository statsRepository;

    
    public DatabaseSeeder(UserRepository userRepository, ScoreRepository scoreRepository, StatsRepository statsRepository) {
        this.userRepository = userRepository;
        this.scoreRepository = scoreRepository;
        this.statsRepository = statsRepository;
        this.deleteAllAndSeed();
    }


    public void deleteAllAndSeed() {
        this.deleteAll();
        this.seed();
    }

    public void deleteAll() {
        userRepository.deleteAll();
        scoreRepository.deleteAll();
        statsRepository.deleteAll();
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
            ScoreEntity.builder().username("user1").score(100).game("game_t5_tests").build(),
            ScoreEntity.builder().username("user2").score(300).game("game_t5_tests").build(),
            ScoreEntity.builder().username("user3").score(100).game("game_test").build(),
            ScoreEntity.builder().username("user3").score(200).game("game_test").build(),
            ScoreEntity.builder().username("user4").score(100).game("game_test").build(),
            ScoreEntity.builder().username("user4").score(200).game("game_test").build(),
            ScoreEntity.builder().username("user1").score(432).game("reactionTime").build(),
            ScoreEntity.builder().username("user2").score(132).game("reactionTime").build(),
            ScoreEntity.builder().username("user3").score(56).game("reactionTime").build(),
            ScoreEntity.builder().username("user4").score(43).game("reactionTime").build(),
            ScoreEntity.builder().username("user4").score(231).game("reactionTime").build(),
        };
        this.scoreRepository.saveAll(List.of(scores));

        StatsEntity[] stats = {
            StatsEntity.builder().username("user1").scores(List.of(scores[0], scores[2])).build(),
            StatsEntity.builder().username("user2").scores(List.of(scores[1], scores[3])).build(),
            StatsEntity.builder().username("user4").scores(List.of(scores[6], scores[7])).build(),
        };
        this.statsRepository.saveAll(List.of(stats));
    }

}
