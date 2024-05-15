package com.tfm.tfmcore.jwt;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tfm.tfmcore.infraestructure.mongodb.entities.user.UserEntity;
import com.tfm.tfmcore.infraestructure.mongodb.repositories.user.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private Optional<UserEntity> userDetails;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername: " + username);
        userDetails = userRepository.findByUsername(username);
        if (userDetails.isPresent()) {
            return User.withUsername(userDetails.get().getUsername())
                    .password(userDetails.get().getPassword())
                    .authorities("read")
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public UserDetails getUserDetail() {
        return User.withUsername(userDetails.get().getUsername())
                .password(userDetails.get().getPassword())
                .authorities("read")
                .build();
    }

}
