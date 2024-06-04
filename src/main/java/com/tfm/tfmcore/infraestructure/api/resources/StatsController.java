package com.tfm.tfmcore.infraestructure.api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tfm.tfmcore.domain.models.core.Stats;
import com.tfm.tfmcore.domain.services.core.StatsService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping(StatsController.STATS)
public class StatsController {

    public static final String STATS = "/stats";
    public static final String STATS_AVERAGE = "/average";
    public static final String STATS_LEVEL = "/top5_level";
    public static final String STATS_TIME = "/top5_time";

    @Autowired
    StatsService statsService;

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping(STATS_AVERAGE)
    public Double readAverageByPlayerGame(@RequestParam String username, @RequestParam String game) {
        return this.statsService.readAverageByPlayerGame(username, game);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping(STATS_LEVEL)
    public Stats readTop5ByPlayerLevelGame(@RequestParam String username, @RequestParam String game) {
        return this.statsService.readTop5ByPlayerLevelGame(username, game);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping(STATS_TIME)
    public Stats readTop5ByPlayerTimeGame(@RequestParam String username, @RequestParam String game) {
        return this.statsService.readTop5ByPlayerTimeGame(username, game);
    }

}
