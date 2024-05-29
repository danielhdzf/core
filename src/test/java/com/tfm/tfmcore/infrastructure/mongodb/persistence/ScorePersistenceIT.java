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
    void testReadNotFound() {
        assertThrows(NotFoundException.class, () -> this.scorePersistence.readByUsername("not_found"));
    }

    @Test
    void testCreateAndRead() {
        Integer size = this.scorePersistence.readByUsername("user1").size();
        Score newScore = new Score("user1", 400, "game_test");
        this.scorePersistence.create(newScore);
        List<Score> scores = this.scorePersistence.readByUsername("user1");
        assertEquals(size + 1, scores.size());
        for (Score score : scores) {
            assertEquals(newScore.getUsername(), score.getUsername());
        }
    }

    @Test
    void testReadByUsernameAndGame() {
        Integer size = this.scorePersistence.readByUsernameAndGame("user1", "game_test").size();
        Score newScore = new Score("user1", 400, "game_test");
        this.scorePersistence.create(newScore);
        List<Score> scores = this.scorePersistence.readByUsernameAndGame("user1", "game_test");
        assertEquals(size + 1, scores.size());
        for (Score score : scores) {
            assertEquals(newScore.getUsername(), score.getUsername());
            assertEquals(newScore.getGame(), score.getGame());
        }
    }

}
