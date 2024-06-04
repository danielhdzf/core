package com.tfm.tfmcore.domain.services.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfm.tfmcore.domain.models.core.Stats;
import com.tfm.tfmcore.domain.persistence.core.StatsPersistence;

@Service
public class StatsService {

    @Autowired
    StatsPersistence statsPersistence;

    public Stats readTop5ByPlayerTimeGame(String username, String game) {
        return this.statsPersistence.readTop5ByPlayerTimeGame(username, game);
    }

    public Stats readTop5ByPlayerLevelGame(String username, String game) {
        return this.statsPersistence.readTop5ByPlayerLevelGame(username, game);
    }

    public Double readAverageByPlayerGame(String username, String game) {
        return this.statsPersistence.readAverageByPlayerGame(username, game);
    }

}
