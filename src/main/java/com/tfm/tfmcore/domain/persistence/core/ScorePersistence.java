package com.tfm.tfmcore.domain.persistence.core;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tfm.tfmcore.domain.models.core.Score;

@Repository
public interface ScorePersistence {

    Score create(Score score);

    List<Score> readTop5ByTimeGame(String game);

    List<Score> readTop5ByLevelGame(String game);

    Double readAverageByGame(String game);
}
