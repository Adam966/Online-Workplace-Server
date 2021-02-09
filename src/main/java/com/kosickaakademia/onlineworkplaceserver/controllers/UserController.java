package com.kosickaakademia.onlineworkplaceserver.controllers;

import com.kosickaakademia.onlineworkplaceserver.entities.UserEntity;
import com.kosickaakademia.onlineworkplaceserver.exceptions.UserException;
import com.kosickaakademia.onlineworkplaceserver.services.UserServiceImpl;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserServiceImpl userService;
    private static final String REGISTER = "register";

    public UserController(UserServiceImpl userServiceImpl) {
        this.userService = userServiceImpl;
    }

    @PostMapping(REGISTER)
    public void registerUser(@RequestBody UserEntity userEntity) {
        try {
            userService.saveUser(userEntity);
        } catch (RuntimeException e) {
            throw new UserException();
        }
    }
}
