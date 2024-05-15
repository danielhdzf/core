package com.tfm.tfmcore.infraestructure.mongodb.entities.user;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tfm.tfmcore.domain.models.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Document
public class UserEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String username;
    @Indexed(unique = true)
    private String email;
    private String password;

    public UserEntity() {}

    public UserEntity(User user) {
        BeanUtils.copyProperties(user, this);
        this.id = UUID.randomUUID().toString();
        this.password = new BCryptPasswordEncoder().encode(this.password);
    }

    public User toUser() {
        User user = new User();
        BeanUtils.copyProperties(this, user);
        user.setPassword("*********");
        return user;
    }

    public void updateFrom(User user) {
        BeanUtils.copyProperties(user, this);
    }
}
