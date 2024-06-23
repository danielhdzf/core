package com.tfm.tfmcore.domain.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.tfm.tfmcore.TestConfig;
import com.tfm.tfmcore.domain.models.user.User;
import com.tfm.tfmcore.infraestructure.api.resources.UserController;
import com.tfm.tfmcore.infrastructure.api.RestClientTestService;


@TestConfig
class GlobalExceptionHandlerTest {

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private RestClientTestService restClientTestService;

    @Test
    void testConflictException() {
        User user = User.builder()
            .username("conflict")
            .email("conflict")
            .password("conflict")
            .build();

        this.webTestClient.post()
                     .uri(UserController.USERS + UserController.USER_SIGNUP)
                     .contentType(MediaType.APPLICATION_JSON)
                     .bodyValue(user)
                     .exchange()
                     .expectStatus().isOk();

        this.webTestClient.post()
                     .uri(UserController.USERS + UserController.USER_SIGNUP)
                     .contentType(MediaType.APPLICATION_JSON)
                     .bodyValue(user)
                     .exchange()
                     .expectStatus().isEqualTo(409);
    }

    @Test
    void testNotFoundException() {
        User user = User.builder()
            .username("notfound")
            .email("notfound")
            .password("notfound")
            .build();
        this.webTestClient
            .post()
            .uri(UserController.USERS + UserController.USER_SIGNUP)
            .bodyValue(user)
            .exchange()
            .expectStatus().isOk();
        this.restClientTestService.setToken(user.getUsername());
        this.restClientTestService.login(this.webTestClient)
            .get()
            .uri(UserController.USERS + UserController.USER_USERNAME, "nonexistentuser")
            .exchange()
            .expectStatus().isNotFound();
    }

    @Test
    void testUnauthorizedException() {
        User user = User.builder()
            .username("unauthorized")
            .email("unauthorized")
            .password("unauthorized")
            .build();
        this.webTestClient
            .post()
            .uri(UserController.USERS + UserController.USER_SIGNUP)
            .bodyValue(user)
            .exchange()
            .expectStatus().isOk();
        this.restClientTestService.setToken(user.getUsername());
        this.restClientTestService.login(this.webTestClient)
            .put()
            .uri(uriBuilder -> uriBuilder
                .path(UserController.USERS + UserController.USER_UPDATE_PASSWORD)
                .queryParam("username", user.getUsername())
                .queryParam("password", "error")
                .queryParam("new_password", "new_password")
                .build())
            .exchange()
            .expectStatus().isUnauthorized();
    }
}

