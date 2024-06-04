package com.tfm.tfmcore.infrastructure.api.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.tfm.tfmcore.TestConfig;
import com.tfm.tfmcore.domain.models.core.Stats;
import com.tfm.tfmcore.infraestructure.api.resources.StatsController;
import com.tfm.tfmcore.infrastructure.api.RestClientTestService;

@TestConfig
class StatsControllerIT {

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private RestClientTestService restClientTestService;

    @Test
    void testReadTop5ByPlayerTimeGame() {
        this.restClientTestService.setToken("user4");
        this.restClientTestService.login(this.webTestClient)
            .get()
            .uri(StatsController.STATS + StatsController.STATS_TIME + "?username=user4&game=game_test")
            .exchange()
            .expectStatus().isOk()
            .expectBody(Stats.class)
            .value(stats -> {
                assertEquals("user4", stats.getUsername());
                assertEquals(2, stats.getScores().size());
                assertEquals(100, stats.getScores().get(0).getScore());
                assertEquals(200, stats.getScores().get(1).getScore());
            });
    }

    @Test
    void testReadTop5ByPlayerLevelGame() {
        this.restClientTestService.setToken("user4");
        this.restClientTestService.login(this.webTestClient)
            .get()
            .uri(StatsController.STATS + StatsController.STATS_LEVEL + "?username=user4&game=game_test")
            .exchange()
            .expectStatus().isOk()
            .expectBody(Stats.class)
            .value(stats -> {
                assertEquals("user4", stats.getUsername());
                assertEquals(2, stats.getScores().size());
                assertEquals(200, stats.getScores().get(0).getScore());
                assertEquals(100, stats.getScores().get(1).getScore());
            });
    }

    @Test
    void testReadAverageByPlayerGame() {
        this.restClientTestService.setToken("user4");
        this.restClientTestService.login(this.webTestClient)
            .get()
            .uri(StatsController.STATS + StatsController.STATS_AVERAGE + "?username=user4&game=game_test")
            .exchange()
            .expectStatus().isOk()
            .expectBody(Double.class)
            .value(Assertions::assertNotNull)
            .value(average -> {
                Assertions.assertEquals(150, average);
            });
    }
}
