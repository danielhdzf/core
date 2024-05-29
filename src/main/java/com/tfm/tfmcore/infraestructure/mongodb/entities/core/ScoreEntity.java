package com.tfm.tfmcore.infraestructure.mongodb.entities.core;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.tfm.tfmcore.domain.models.core.Score;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Document
public class ScoreEntity {

    @Id
    private String id;
    private String username;
    private Integer score;
    private LocalDateTime creationDate;
    private String game;

    public ScoreEntity(Score score) {
        BeanUtils.copyProperties(score, this);
        this.creationDate = LocalDateTime.now();
    }

    public Score toScore() {
        Score score = new Score();
        BeanUtils.copyProperties(this, score);
        return score;
    }

}
