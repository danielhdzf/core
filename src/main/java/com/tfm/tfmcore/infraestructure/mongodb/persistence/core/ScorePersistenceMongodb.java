package com.tfm.tfmcore.infraestructure.mongodb.persistence.core;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tfm.tfmcore.domain.exceptions.NotFoundException;
import com.tfm.tfmcore.domain.models.core.Score;
import com.tfm.tfmcore.domain.persistence.core.ScorePersistence;
import com.tfm.tfmcore.infraestructure.mongodb.entities.core.ScoreEntity;
import com.tfm.tfmcore.infraestructure.mongodb.repositories.core.ScoreRepository;
import com.tfm.tfmcore.infraestructure.mongodb.repositories.user.UserRepository;

public class ScorePersistenceMongodb implements ScorePersistence {

    @Autowired
    ScoreRepository scoreRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Score create(Score score) {
        if (this.userRepository.findByUsername(score.getUsername()).isEmpty()) {
            throw new NotFoundException("User with username " + score.getUsername() + " not found");
        }
        return this.scoreRepository
            .save(new ScoreEntity(score))
            .toScore();
    }

    @Override
    public List<Score> readByUsername(String username) {
        return this.scoreRepository
            .findByUsername(username)
            .stream()
            .map(ScoreEntity::toScore)
            .toList();
    }

    @Override
    public List<Score> readByUsernameAndGame(String username, String game) {
        return this.scoreRepository
            .findByUsername(username)
            .stream()
            .filter(scoreEntity -> scoreEntity.getGame().equals(game))
            .map(ScoreEntity::toScore)
            .toList();
    }

}
