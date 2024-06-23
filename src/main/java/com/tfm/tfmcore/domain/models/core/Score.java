package com.tfm.tfmcore.domain.models.core;

import com.tfm.tfmcore.configuration.Generated;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Generated
public class Score {

    @NotBlank
    private String username;
    @NotNull
    @Min(0)
    private Integer score;
    @NotBlank
    private String game;
}
