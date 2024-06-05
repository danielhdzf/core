package com.tfm.tfmcore.infraestructure.mongodb.persistence.core;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tfm.tfmcore.domain.exceptions.NotFoundException;
import com.tfm.tfmcore.domain.models.core.Score;
import com.tfm.tfmcore.domain.models.core.Stats;
import com.tfm.tfmcore.domain.persistence.core.ScorePersistence;
import com.tfm.tfmcore.infraestructure.mongodb.entities.core.ScoreEntity;
import com.tfm.tfmcore.infraestructure.mongodb.entities.core.StatsEntity;
import com.tfm.tfmcore.infraestructure.mongodb.repositories.core.ScoreRepository;
import com.tfm.tfmcore.infraestructure.mongodb.repositories.core.StatsRepository;
import com.tfm.tfmcore.infraestructure.mongodb.repositories.user.UserRepository;

@Repository
public class ScorePersistenceMongodb implements ScorePersistence {

    @Autowired
    ScoreRepository scoreRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StatsRepository statsRepository;

    @Override
    public Score create(Score score) {
        if (this.userRepository.findByUsername(score.getUsername()).isEmpty()) {
            throw new NotFoundException("User with username " + score.getUsername() + " not found");
        } else if (scoreRepository.findByUsername(score.getUsername()).isEmpty()) {
            this.statsRepository.save(new StatsEntity(new Stats(score.getUsername(), List.of(score))));
        } else {
            StatsEntity stats = this.statsRepository.findByUsername(score.getUsername());
            stats.getScores().add(new ScoreEntity(score));
            this.statsRepository.save(stats);
        }
        return this.scoreRepository
            .save(new ScoreEntity(score))
            .toScore();
    }

    @Override
    public List<Score> readTop5ByLevelGame(String game) {
        return this.scoreRepository
            .findByGame(game)
            .stream()
            .sorted((a, b) -> b.getScore() - a.getScore())
            .limit(5)
            .map(ScoreEntity::toScore)
            .toList();
    }

    @Override
    public List<Score> readTop5ByTimeGame(String game) {
        return this.scoreRepository
            .findByGame(game)
            .stream()
            .sorted((a, b) -> a.getScore() - b.getScore())
            .limit(5)
            .map(ScoreEntity::toScore)
            .toList();
    }

    @Override
    public Double readAverageByGame(String game) {
        return this.scoreRepository
            .findByGame(game)
            .stream()
            .mapToDouble(ScoreEntity::getScore)
            .average()
            .orElse(0);
    }

}
