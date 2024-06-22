package com.tfm.tfmcore.infraestructure.mongodb.entities.core;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.tfm.tfmcore.domain.models.core.Score;
import com.tfm.tfmcore.domain.models.core.Stats;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
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

    public Stats toStats() {
        Stats stats = new Stats();
        List<Score> scoreList = this.scores.stream().map(ScoreEntity::toScore).toList();
        stats.setUsername(this.username);
        stats.setScores(scoreList);
        return stats;
    }

}
