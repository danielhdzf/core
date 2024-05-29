package com.tfm.tfmcore.infraestructure.mongodb.repositories.core;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tfm.tfmcore.infraestructure.mongodb.entities.core.ScoreEntity;

public interface ScoreRepository extends MongoRepository<ScoreEntity, String>{

    Optional<ScoreEntity> findByUsername(String username);
}
