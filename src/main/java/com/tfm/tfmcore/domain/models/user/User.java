package com.tfm.tfmcore.domain.models.user;

import javax.validation.constraints.NotBlank;

import com.tfm.tfmcore.configuration.Generated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Generated
public class User {

    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @NotBlank
    private String password;
    
}
