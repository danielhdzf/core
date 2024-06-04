package com.tfm.tfmcore.domain.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tfm.tfmcore.TestConfig;
import com.tfm.tfmcore.domain.models.core.Stats;
import com.tfm.tfmcore.domain.services.core.StatsService;

@TestConfig
class StatsServiceIT {

    @Autowired
    StatsService statsService;

    @Test
    public void testReadTop5ByPlayerTimeGame() {
        Stats stats = this.statsService.readTop5ByPlayerTimeGame("user4", "game_test");
        assertEquals("user4", stats.getUsername());
        assertEquals(2, stats.getScores().size());
        assertEquals(100, stats.getScores().get(0).getScore());
        assertEquals(200, stats.getScores().get(1).getScore());
    }

    @Test
    public void testReadTop5ByPlayerLevelGame() {
        Stats stats = this.statsService.readTop5ByPlayerLevelGame("user4", "game_test");
        assertEquals("user4", stats.getUsername());
        assertEquals(2, stats.getScores().size());
        assertEquals(200, stats.getScores().get(0).getScore());
        assertEquals(100, stats.getScores().get(1).getScore());
    }

    @Test
    public void testReadAverageByPlayerGame() {
        Double average = this.statsService.readAverageByPlayerGame("user4", "game_test");
        assertEquals(150, average);
    }

    @Test
    public void testReadAverageByPlayerGameEmpty() {
        Double average = this.statsService.readAverageByPlayerGame("user4", "game_not_found");
        assertEquals(0.0, average);
    }

}
