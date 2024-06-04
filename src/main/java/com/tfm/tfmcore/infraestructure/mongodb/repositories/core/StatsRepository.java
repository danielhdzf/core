package com.tfm.tfmcore.infraestructure.mongodb.repositories.core;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tfm.tfmcore.infraestructure.mongodb.entities.core.StatsEntity;

public interface StatsRepository extends MongoRepository<StatsEntity, String>{
    StatsEntity findByUsername(String username);

}
