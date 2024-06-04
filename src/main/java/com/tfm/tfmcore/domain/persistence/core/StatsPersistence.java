package com.tfm.tfmcore.domain.persistence.core;

import org.springframework.stereotype.Repository;

import com.tfm.tfmcore.domain.models.core.Stats;

@Repository
public interface StatsPersistence {

    void create(Stats stats);

    Stats readTop5ByPlayerTimeGame(String username, String game);

    Stats readTop5ByPlayerLevelGame(String username, String game);

    Double readAverageByPlayerGame(String username, String game);
}
