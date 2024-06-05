package com.tfm.tfmcore.infraestructure.mongodb.persistence.core;

import com.tfm.tfmcore.domain.models.core.Stats;
import com.tfm.tfmcore.domain.models.core.Score;
import com.tfm.tfmcore.domain.persistence.core.StatsPersistence;
import com.tfm.tfmcore.infraestructure.mongodb.entities.core.ScoreEntity;
import com.tfm.tfmcore.infraestructure.mongodb.entities.core.StatsEntity;
import com.tfm.tfmcore.infraestructure.mongodb.repositories.core.StatsRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StatsPersistenceMongodb implements StatsPersistence{
    
    @Autowired
    StatsRepository statsRepository;

    @Override
    public void create(Stats stats) {
        this.statsRepository.save(new StatsEntity(stats));
    }

    @Override
    public Stats readTop5ByPlayerTimeGame(String username, String game) {
        StatsEntity userStats = this.statsRepository.findByUsername(username);
        List<Score> scores = userStats.getScores()
            .stream()
            .filter(score -> score.getGame().equals(game))
            .sorted((a, b) -> a.getScore() - b.getScore())
            .map(ScoreEntity::toScore)
            .toList();
        return Stats.builder()
            .username(username)
            .scores(scores)
            .build();
    }

    @Override
    public Stats readTop5ByPlayerLevelGame(String username, String game) {
        StatsEntity userStats = this.statsRepository.findByUsername(username);
        List<Score> scores = userStats.getScores()
            .stream()
            .filter(score -> score.getGame().equals(game))
            .sorted((a, b) -> b.getScore() - a.getScore())
            .map(ScoreEntity::toScore)
            .toList();
        return Stats.builder()
            .username(username)
            .scores(scores)
            .build();
    }

    @Override
    public Double readAverageByPlayerGame(String username, String game) {
        StatsEntity userStats = this.statsRepository.findByUsername(username);
        return userStats
            .getScores()
            .stream()
            .filter(score -> score.getGame().equals(game))
            .mapToDouble(ScoreEntity::getScore)
            .average()
            .orElse(0.0);
    }

}
