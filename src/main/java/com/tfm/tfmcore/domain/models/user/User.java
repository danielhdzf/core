package com.tfm.tfmcore.domain.models.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {

    @NotBlank
    private String username;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    
}
