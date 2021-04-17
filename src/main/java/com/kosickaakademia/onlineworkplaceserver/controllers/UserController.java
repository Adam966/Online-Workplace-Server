package com.kosickaakademia.onlineworkplaceserver.controllers;

import com.kosickaakademia.onlineworkplaceserver.dto.UserDTO;
import com.kosickaakademia.onlineworkplaceserver.entities.UserEntity;
import com.kosickaakademia.onlineworkplaceserver.exceptions.UserException;
import com.kosickaakademia.onlineworkplaceserver.services.UserServiceImpl;

import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserServiceImpl userService;
    private static final String REGISTER = "register";
    private static final String FIND_USER = "users";
    private static final String CHANGE_EMAIL = "change-email/user/{userId}";

    public UserController(UserServiceImpl userServiceImpl) {
        this.userService = userServiceImpl;
    }

    @PostMapping(REGISTER)
    public void registerUser(@RequestBody UserEntity userEntity) {
        userService.saveUser(userEntity);
    }

    @GetMapping(FIND_USER)
    public ResponseEntity<List<UserDTO>> findUserByName(@RequestParam String email) {
        return ResponseEntity.ok(userService.getUsersByEmail(email));
    }

    @PostMapping(CHANGE_EMAIL)
    public void changeEmail(@PathVariable Long userId, @RequestBody String email) {
        userService.changeEmail(userId, email);
    }

}
