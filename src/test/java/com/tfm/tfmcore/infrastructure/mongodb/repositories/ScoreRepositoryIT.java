package com.tfm.tfmcore.infrastructure.mongodb.repositories;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tfm.tfmcore.TestConfig;
import com.tfm.tfmcore.domain.models.core.Score;
import com.tfm.tfmcore.infraestructure.mongodb.repositories.core.ScoreRepository;

@TestConfig
class ScoreRepositoryIT {

    @Autowired
    private ScoreRepository scoreRepository;

    @Test
    public void testFindByUsername() {
        assertTrue(this.scoreRepository.findByUsername("user1").isPresent());
        Score score = this.scoreRepository.findByUsername("user1").get().toScore();
        assertTrue(score.getUsername().equals("user1"));
        assertTrue(score.getScore().equals(100));
    }

}
