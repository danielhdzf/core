package com.tfm.tfmcore.infraestructure.mongodb.repositories.core;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tfm.tfmcore.infraestructure.mongodb.entities.core.ScoreEntity;

public interface ScoreRepository extends MongoRepository<ScoreEntity, String>{

    List<ScoreEntity> findByUsername(String username);
    List<ScoreEntity> findByGame(String game);
}
