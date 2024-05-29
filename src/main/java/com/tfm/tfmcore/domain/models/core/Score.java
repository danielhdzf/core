package com.tfm.tfmcore.domain.models.core;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Score {

    @NotBlank
    private String username;
    @NotBlank
    private Integer score;
    @NotBlank
    private String game;
}
