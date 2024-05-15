package com.tfm.tfmcore.infraestructure.api.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.tfm.tfmcore.domain.models.user.User;
import com.tfm.tfmcore.domain.services.user.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(UserController.USERS)
public class UserController {

    public static final String USERS = "/users"; 
    public static final String USER_USERNAME = "/{username}";
    public static final String USER_SIGNUP = "/signup";
    public static final String USER_LOGIN = "/login";

    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(USER_SIGNUP)
    public User signUp(@Valid @RequestBody User user) {
        return this.userService.create(user);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PutMapping(USER_USERNAME)
    public User update(@PathVariable String username, @Valid @RequestBody User user) {
        return this.userService.update(username, user);
    }
    
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping(USER_USERNAME)
    public void delete(@PathVariable String username) {
        this.userService.delete(username);
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping(USER_USERNAME)
    public User read(@PathVariable String username) {
        return this.userService.readByUsername(username);
    }

    @GetMapping(USER_LOGIN)
    public Optional<String> getMethodName(@RequestParam String username, @RequestParam String password) {
        return this.userService.login(username, password);
    }
    
}
