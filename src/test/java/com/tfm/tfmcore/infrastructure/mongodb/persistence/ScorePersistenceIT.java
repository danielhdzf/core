package com.tfm.tfmcore.infrastructure.mongodb.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tfm.tfmcore.TestConfig;
import com.tfm.tfmcore.domain.exceptions.NotFoundException;
import com.tfm.tfmcore.domain.models.core.Score;
import com.tfm.tfmcore.domain.persistence.core.ScorePersistence;

@TestConfig
class ScorePersistenceIT {

    @Autowired
    private ScorePersistence scorePersistence;

    @Test
    void testCreateUserNotFound() {
        Score score = new Score("not_found", 100, "game_test");
        assertThrows(NotFoundException.class, () -> this.scorePersistence.create(score));
    }

    @Test
    void testCreate() {
        Score score = new Score("user4", 100, "game_test");
        Score created = this.scorePersistence.create(score);
        assertEquals(score, created);
    }

    @Test
    void testReadTop5ByLevelGame() {
        List<Score> scores = this.scorePersistence.readTop5ByLevelGame("game_t5_tests");
        assertEquals(2, scores.size());
        assertEquals("user2", scores.get(0).getUsername());
        assertEquals(300, scores.get(0).getScore());
        assertEquals("user1", scores.get(1).getUsername());
        assertEquals(100, scores.get(1).getScore());
    }

    @Test
    void testReadTop5ByTimeGame() {
        List<Score> scores = this.scorePersistence.readTop5ByTimeGame("game_t5_tests");
        assertEquals(2, scores.size());
        assertEquals("user1", scores.get(0).getUsername());
        assertEquals(100, scores.get(0).getScore());
        assertEquals("user2", scores.get(1).getUsername());
        assertEquals(300, scores.get(1).getScore());
    }

    @Test
    void testReadAverageByGame() {
        Double average = this.scorePersistence.readAverageByGame("game_t5_tests");
        assertEquals(200, average);
    }

    @Test
    void testReadAverageByGameEmpty() {
        Double average = this.scorePersistence.readAverageByGame("game_not_found");
        assertEquals(0.0, average);
    }
}
