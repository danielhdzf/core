package com.tfm.tfmcore.infraestructure.mongodb.entities.core;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.tfm.tfmcore.configuration.Generated;
import com.tfm.tfmcore.domain.models.core.Stats;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Generated
@Document(collection = "stats")
public class StatsEntity {
    @Id
    private String id;
    private String username;
    private List<ScoreEntity> scores;

    public StatsEntity() {
    }

    public StatsEntity(Stats stats) {
        this.username = stats.getUsername();
        this.scores = stats.getScores().stream().map(ScoreEntity::new).toList();
    }
}
