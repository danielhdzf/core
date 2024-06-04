package com.tfm.tfmcore.domain.models.core;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Stats {

    @NotBlank
    private String username;
    private List<Score> scores;

}
