package com.tfm.tfmcore.domain.services.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfm.tfmcore.domain.models.core.Score;
import com.tfm.tfmcore.domain.persistence.core.ScorePersistence;

import java.util.List;
@Service
public class ScoreService {

    @Autowired
    ScorePersistence scorePersistence;

    public Score create(Score score) {
        return this.scorePersistence.create(score);
    }

    public List<Score> readTop5ByTimeGame(String game) {
        return this.scorePersistence.readTop5ByTimeGame(game);
    }

    public List<Score> readTop5ByLevelGame(String game) {
        return this.scorePersistence.readTop5ByLevelGame(game);
    }

    public Double readAverageByGame(String game) {
        return this.scorePersistence.readAverageByGame(game);
    }

}
