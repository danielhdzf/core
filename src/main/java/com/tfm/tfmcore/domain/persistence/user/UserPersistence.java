package com.tfm.tfmcore.domain.persistence.user;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.tfm.tfmcore.domain.models.user.User;

@Repository
public interface UserPersistence {

    User create(User user);

    User read(String username);

    User update(String username, User user);

    void delete(String username);

    Optional<String> login(String username, String password);
}
