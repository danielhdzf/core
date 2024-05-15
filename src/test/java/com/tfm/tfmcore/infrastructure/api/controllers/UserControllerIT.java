package com.tfm.tfmcore.infrastructure.api.controllers;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.tfm.tfmcore.TestConfig;
import com.tfm.tfmcore.domain.models.user.User;
import com.tfm.tfmcore.domain.services.user.UserService;
import com.tfm.tfmcore.infrastructure.api.RestClientTestService;

import com.tfm.tfmcore.infraestructure.api.resources.UserController;


@TestConfig
class UserControllerIT {

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private RestClientTestService restClientTestService;
    @Autowired
    private UserService userService;

    @AfterAll
    void cleanUp() {
        this.userService.delete("test");
        this.userService.delete("test_login");
        this.userService.delete("test_read");
        this.userService.delete("update");
    }

    @Test
    void testSignUpAndDelete() {
        User user = User.builder()
            .username("test")
            .email("test")
            .password("test")
            .build();
        this.webTestClient
            .post()
            .uri(UserController.USERS + UserController.USER_SIGNUP)
            .bodyValue(user)
            .exchange()
            .expectStatus().isOk()
            .expectBody(User.class)
            .value(Assertions::assertNotNull)
            .value(returnedUser -> {
                Assertions.assertEquals(user.getUsername(), returnedUser.getUsername());
                Assertions.assertEquals(user.getEmail(), returnedUser.getEmail());
            });
        this.restClientTestService.setToken(user.getUsername());
        this.restClientTestService.login(this.webTestClient)
            .delete()
            .uri(UserController.USERS + UserController.USER_USERNAME, user.getUsername())
            .exchange()
            .expectStatus().isOk();
    }

    @Test
    void testLogin() {
        User user = User.builder()
            .username("test_login")
            .email("test_login")
            .password("test_login")
            .build();
        this.webTestClient
            .post()
            .uri(UserController.USERS + UserController.USER_SIGNUP)
            .bodyValue(user)
            .exchange()
            .expectStatus().isOk();
        this.webTestClient
            .get()
            .uri(uriBuilder -> uriBuilder
                .path(UserController.USERS + UserController.USER_LOGIN)
                .queryParam("username", user.getUsername())
                .queryParam("password", user.getPassword())
                .build())
            .exchange()
            .expectStatus().isOk()
            .expectBody(String.class)
            .value(Assertions::assertNotNull);
    }

    @Test
    void testRead() {
        
        User user = User.builder()
            .username("test_read")
            .email("test_read")
            .password("test_read")
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
            .uri(UserController.USERS + UserController.USER_USERNAME, user.getUsername())
            .exchange()
            .expectStatus().isOk()
            .expectBody(User.class)
            .value(Assertions::assertNotNull)
            .value(returnedUser -> {
                Assertions.assertEquals(user.getUsername(), returnedUser.getUsername());
                Assertions.assertEquals(user.getEmail(), returnedUser.getEmail());
            });
    }

    @Test
    void testUpdate() {
        User user = User.builder()
            .username("test_update")
            .email("test_update")
            .password("test_update")
            .build();
        this.webTestClient
            .post()
            .uri(UserController.USERS + UserController.USER_SIGNUP)
            .bodyValue(user)
            .exchange()
            .expectStatus().isOk();
        this.restClientTestService.setToken(user.getUsername());
        User updatedUser = User.builder()
            .username("update")
            .email("update")
            .password("test_update")
            .build();
        this.restClientTestService.login(this.webTestClient)
            .put()
            .uri(UserController.USERS + UserController.USER_USERNAME, user.getUsername())
            .bodyValue(updatedUser)
            .exchange()
            .expectStatus().isOk()
            .expectBody(User.class)
            .value(Assertions::assertNotNull)
            .value(returnedUser -> {
                Assertions.assertEquals(updatedUser.getUsername(), returnedUser.getUsername());
                Assertions.assertEquals(updatedUser.getEmail(), returnedUser.getEmail());
            });
    }
}
