package com.tfm.tfmcore.infrastructure.mongodb.repositories;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tfm.tfmcore.TestConfig;
import com.tfm.tfmcore.infraestructure.mongodb.entities.core.ScoreEntity;
import com.tfm.tfmcore.infraestructure.mongodb.repositories.core.ScoreRepository;

@TestConfig
class ScoreRepositoryIT {

    @Autowired
    private ScoreRepository scoreRepository;

    @Test
    public void testFindByUsername() {
        assertTrue(this.scoreRepository.findByUsername("user1").size() > 0);
        List<ScoreEntity> scoresEntities = this.scoreRepository.findByUsername("user1");
        assertTrue(scoresEntities.stream().allMatch(s -> s.getUsername().equals("user1")));
    }

    @Test
    public void testFindByGame() {
        assertTrue(this.scoreRepository.findByGame("game1").size() > 0);
        List<ScoreEntity> scoresEntities = this.scoreRepository.findByGame("game1");
        assertTrue( scoresEntities.stream().allMatch(s -> s.getGame().equals("game1")));
    }

}
