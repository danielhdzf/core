package com.tfm.tfmcore.infrastructure.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.tfm.tfmcore.jwt.JwtUtil;

@Service
public class RestClientTestService {

    @Autowired
    private JwtUtil jwtUtil;
    private String token;

    public String getToken() {
        return this.token;
    }

    public void setToken(String username) {
        this.token = jwtUtil.generateToken(username);
    }

    public WebTestClient login(WebTestClient webTestClient) {
        return webTestClient.mutate()
                .defaultHeader("Authorization", "Bearer " + this.token).build();
    }
}
