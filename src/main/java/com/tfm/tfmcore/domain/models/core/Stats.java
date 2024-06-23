package com.tfm.tfmcore.domain.models.core;

import java.util.List;

import com.tfm.tfmcore.configuration.Generated;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Generated
public class Stats {

    @NotBlank
    private String username;
    private List<Score> scores;

}
