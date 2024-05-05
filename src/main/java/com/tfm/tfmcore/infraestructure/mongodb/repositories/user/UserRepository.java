package com.tfm.tfmcore.infraestructure.mongodb.repositories.user;


import java.util.Optional;
import com.tfm.tfmcore.infraestructure.mongodb.entities.user.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, String>{

    Optional<UserEntity> findByUsername(String username);

    void deleteByUsername(String username);
}
