package com.tfm.tfmcore.infrastructure.mongodb.repositories;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tfm.tfmcore.TestConfig;
import com.tfm.tfmcore.infraestructure.mongodb.entities.core.StatsEntity;
import com.tfm.tfmcore.infraestructure.mongodb.repositories.core.StatsRepository;


@TestConfig
class StatsRepositoryIT {

    @Autowired
    private StatsRepository statsRepository;

    @Test
    public void testFindByUsername() {
        StatsEntity statsEntities = this.statsRepository.findByUsername("user1");
        assertTrue(statsEntities.getUsername().equals("user1"));
    }

}
