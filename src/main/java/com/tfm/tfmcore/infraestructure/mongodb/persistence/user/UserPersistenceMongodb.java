package com.tfm.tfmcore.infraestructure.mongodb.persistence.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tfm.tfmcore.domain.exceptions.ConflictException;
import com.tfm.tfmcore.domain.exceptions.NotFoundException;
import com.tfm.tfmcore.domain.models.user.User;
import com.tfm.tfmcore.domain.persistence.user.UserPersistence;
import com.tfm.tfmcore.infraestructure.mongodb.entities.user.UserEntity;
import com.tfm.tfmcore.infraestructure.mongodb.repositories.user.UserRepository;

@Repository
public class UserPersistenceMongodb implements UserPersistence {
    
    private final UserRepository userRepository;

    @Autowired
    public UserPersistenceMongodb(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User create(User user) {
        if (this.userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new ConflictException("User with username " + user.getUsername() + " already exists");
        }
        return this.userRepository
            .save(new UserEntity(user))
            .toUser();
    }

    @Override
    public User read(String username) {
        return this.userRepository
            .findByUsername(username)
            .orElseThrow(() -> new NotFoundException("User with username " + username + " not found"))
            .toUser();
    }

    @Override
    public User update(String username, User user) {
        UserEntity existingUserEntity = this.userRepository.findByUsername(username)
            .orElseThrow(() -> new NotFoundException("User with username " + username + " not found"));
        if (!username.equals(user.getUsername())) {
            if (this.userRepository.findByUsername(user.getUsername()).isPresent()) {
                throw new ConflictException("User with username " + user.getUsername() + " already exists");
            }
        }
        existingUserEntity.updateFrom(user);
        return this.userRepository.save(existingUserEntity).toUser();
    }

    @Override
    public void delete(String username) {
        this.userRepository.deleteByUsername(username);
    }

}
