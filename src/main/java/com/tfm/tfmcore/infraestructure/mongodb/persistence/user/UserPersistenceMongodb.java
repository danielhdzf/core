package com.tfm.tfmcore.infraestructure.mongodb.persistence.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tfm.tfmcore.domain.exceptions.ConflictException;
import com.tfm.tfmcore.domain.exceptions.NotFoundException;
import com.tfm.tfmcore.domain.exceptions.UnauthorizedException;
import com.tfm.tfmcore.domain.models.user.User;
import com.tfm.tfmcore.domain.persistence.user.UserPersistence;
import com.tfm.tfmcore.infraestructure.mongodb.entities.user.UserEntity;
import com.tfm.tfmcore.infraestructure.mongodb.repositories.user.UserRepository;
import com.tfm.tfmcore.jwt.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Repository
public class UserPersistenceMongodb implements UserPersistence {
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;


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

    @Override
    public Optional<String> login(String username, String password) {
        UserEntity userEntity = this.userRepository.findByUsername(username)
            .orElseThrow(() -> new NotFoundException("User with username " + username + " not found"));
        if (new BCryptPasswordEncoder().matches(password, userEntity.getPassword())){
            return Optional.of(jwtUtil.generateToken(username));
        }
        else {
            throw new UnauthorizedException(password + " is not the correct password");
        }
    }

}
