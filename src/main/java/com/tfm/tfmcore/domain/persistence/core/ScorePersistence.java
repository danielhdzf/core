package com.tfm.tfmcore.domain.persistence.core;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tfm.tfmcore.domain.models.core.Score;

@Repository
public interface ScorePersistence {

    Score create(Score score);

    List<Score> readByUsername(String username);

    List<Score> readByUsernameAndGame(String username, String game);
}
