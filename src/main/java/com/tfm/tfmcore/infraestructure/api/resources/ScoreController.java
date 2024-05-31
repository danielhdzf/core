package com.tfm.tfmcore.infraestructure.api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tfm.tfmcore.domain.models.core.Score;
import com.tfm.tfmcore.domain.services.core.ScoreService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping(ScoreController.SCORES)
public class ScoreController {

    public static final String SCORES = "/scores";
    public static final String SCORE_AVERAGE = "/average";
    public static final String SCORE_LEVEL = "/top5_level";
    public static final String SCORE_TIME = "/top5_time";

    @Autowired
    ScoreService scoreService;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping(produces = {"application/json"})
    public Score create(@Valid @RequestBody Score score) {
        return this.scoreService.create(score);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping(SCORE_AVERAGE)
    public Double readAverageByGame(@RequestParam String game) {
        return this.scoreService.readAverageByGame(game);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping(SCORE_LEVEL)
    public Object readTop5ByLevelGame(@RequestParam String game) {
        return this.scoreService.readTop5ByLevelGame(game);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping(SCORE_TIME)
    public Object readTop5ByTimeGame(@RequestParam String game) {
        return this.scoreService.readTop5ByTimeGame(game);
    }
}
