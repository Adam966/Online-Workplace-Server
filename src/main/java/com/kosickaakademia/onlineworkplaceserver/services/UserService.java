package com.kosickaakademia.onlineworkplaceserver.services;

import com.kosickaakademia.onlineworkplaceserver.dto.UserDTO;
import com.kosickaakademia.onlineworkplaceserver.entities.UserEntity;

import java.util.List;


public interface UserService {
    void saveUser(UserEntity userEntity);
    List<UserDTO> getUsersByEmail(String name);
}
