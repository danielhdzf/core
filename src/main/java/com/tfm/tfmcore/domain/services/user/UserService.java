package com.tfm.tfmcore.domain.services.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfm.tfmcore.domain.persistence.user.UserPersistence;
import com.tfm.tfmcore.domain.models.user.User;

@Service
public class UserService {

    @Autowired
    UserPersistence userPersistence;

    public User create(User user) {
        return this.userPersistence.create(user);
    }

    public User update(String username, User user) {
        return this.userPersistence.update(username, user);
    }
    public void delete(String username) {
        this.userPersistence.delete(username);
    }

    public User readByUsername(String username) {
        return this.userPersistence.read(username);
    }

    public void updatePassword(String username, String password, String newPassword) {
        this.userPersistence.updatePassword(username, password, newPassword);
    }

    public Optional<String> login(String username, String password) {
        return this.userPersistence.login(username, password);
    }

}
