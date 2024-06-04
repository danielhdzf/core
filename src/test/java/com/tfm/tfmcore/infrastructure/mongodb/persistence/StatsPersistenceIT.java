package com.tfm.tfmcore.infrastructure.mongodb.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

import com.tfm.tfmcore.TestConfig;
import com.tfm.tfmcore.domain.models.core.Stats;
import com.tfm.tfmcore.domain.persistence.core.StatsPersistence;
import com.tfm.tfmcore.infraestructure.mongodb.entities.core.ScoreEntity;
import com.tfm.tfmcore.infraestructure.mongodb.repositories.core.ScoreRepository;

@TestConfig
@TestMethodOrder(OrderAnnotation.class)
class StatsPersistenceIT {

    @Autowired
    private StatsPersistence statsPersistence;
    @Autowired
    private ScoreRepository scoreRepository;

    @Test
    @Order(1)
    public void testCreate() {
        Stats stats = new Stats("user3", this.scoreRepository.findByUsername("user3").stream().map(ScoreEntity::toScore).toList());
        this.statsPersistence.create(stats);
    }

    @Test
    @Order(2)
    public void testReadTop5ByPlayerTimeGame() {
        Stats stats = this.statsPersistence.readTop5ByPlayerTimeGame("user3", "game_test");
        assertEquals("user3", stats.getUsername());
        assertEquals(2, stats.getScores().size());
        assertEquals(100, stats.getScores().get(0).getScore());
        assertEquals(200, stats.getScores().get(1).getScore());
    }

    @Test
    @Order(3)
    public void testReadTop5ByPlayerLevelGame() {
        Stats stats = this.statsPersistence.readTop5ByPlayerLevelGame("user3", "game_test");
        assertEquals("user3", stats.getUsername());
        assertEquals(2, stats.getScores().size());
        assertEquals(200, stats.getScores().get(0).getScore());
        assertEquals(100, stats.getScores().get(1).getScore());
    }

    @Test
    @Order(4)
    public void testReadAverageByPlayerGame() {
        Double average = this.statsPersistence.readAverageByPlayerGame("user3", "game_test");
        assertEquals(150, average);
    }

    @Test
    @Order(5)
    public void testReadAverageByPlayerGameEmpty() {
        Double average = this.statsPersistence.readAverageByPlayerGame("user3", "game_not_found");
        assertEquals(0.0, average);
    }
}
