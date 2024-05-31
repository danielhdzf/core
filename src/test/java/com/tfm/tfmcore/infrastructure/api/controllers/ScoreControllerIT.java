package com.tfm.tfmcore.infrastructure.api.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.tfm.tfmcore.TestConfig;
import com.tfm.tfmcore.domain.models.core.Score;
import com.tfm.tfmcore.infraestructure.api.resources.ScoreController;
import com.tfm.tfmcore.infrastructure.api.RestClientTestService;

@TestConfig
@TestMethodOrder(OrderAnnotation.class)
class ScoreControllerIT {

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private RestClientTestService restClientTestService;

    private static String user = "user1";
    private static Score score1;
    private static Score score2;

    @BeforeAll
    static void beforeAll() {
        score1 = Score.builder()
            .username(user)
            .game("test")
            .score(100)
            .build();
        score2 = Score.builder()
            .username(user)
            .game("test")
            .score(300)
            .build();
    }

    @Test
    @Order(1)
    void testCreate() {
        this.restClientTestService.setToken(ScoreControllerIT.score1.getUsername());
        this.restClientTestService.login(this.webTestClient)
            .post()
            .uri(ScoreController.SCORES)
            .bodyValue(ScoreControllerIT.score1)
            .exchange()
            .expectStatus().isOk()
            .expectBody(Score.class)
            .value(Assertions::assertNotNull)
            .value(returnedScore -> {
                Assertions.assertEquals(ScoreControllerIT.score1.getGame(), returnedScore.getGame());
                Assertions.assertEquals(ScoreControllerIT.score1.getScore(), returnedScore.getScore());
                Assertions.assertEquals(ScoreControllerIT.score1.getUsername(), returnedScore.getUsername());
            });
        this.restClientTestService.setToken(ScoreControllerIT.score2.getUsername());
        this.restClientTestService.login(this.webTestClient)
            .post()
            .uri(ScoreController.SCORES)
            .bodyValue(ScoreControllerIT.score2)
            .exchange()
            .expectStatus().isOk()
            .expectBody(Score.class)
            .value(Assertions::assertNotNull)
            .value(returnedScore -> {
                Assertions.assertEquals(ScoreControllerIT.score2.getGame(), returnedScore.getGame());
                Assertions.assertEquals(ScoreControllerIT.score2.getScore(), returnedScore.getScore());
                Assertions.assertEquals(ScoreControllerIT.score2.getUsername(), returnedScore.getUsername());
            });
    }

    @Test
    @Order(2)
    void testReadTop5ByTimeGame() {
        this.restClientTestService.setToken(ScoreControllerIT.user);
        this.restClientTestService.login(this.webTestClient)
            .get()
            .uri(ScoreController.SCORES + ScoreController.SCORE_TIME + "?game=test")
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(Score.class)
            .value(Assertions::assertNotNull)
            .value(scores -> {
                Assertions.assertEquals(2, scores.size());
                Assertions.assertEquals(ScoreControllerIT.score1.getScore(), scores.get(0).getScore());
                Assertions.assertEquals(ScoreControllerIT.score2.getScore(), scores.get(1).getScore());
            });
    }

    @Test
    @Order(3)
    void testReadTop5ByLevelGame() {
        this.restClientTestService.setToken(ScoreControllerIT.user);
        this.restClientTestService.login(this.webTestClient)
            .get()
            .uri(ScoreController.SCORES + ScoreController.SCORE_LEVEL + "?game=test")
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(Score.class)
            .value(Assertions::assertNotNull)
            .value(scores -> {
                Assertions.assertEquals(2, scores.size());
                Assertions.assertEquals(ScoreControllerIT.score1.getScore(), scores.get(1).getScore());
                Assertions.assertEquals(ScoreControllerIT.score2.getScore(), scores.get(0).getScore());
            });
    }

    @Test
    @Order(4)
    void testReadAverageByGame() {
        this.restClientTestService.setToken(ScoreControllerIT.user);
        this.restClientTestService.login(this.webTestClient)
            .get()
            .uri(ScoreController.SCORES + ScoreController.SCORE_AVERAGE + "?game=test")
            .exchange()
            .expectStatus().isOk()
            .expectBody(Double.class)
            .value(Assertions::assertNotNull)
            .value(average -> {
                Assertions.assertEquals(200, average);
            });
    }

}
