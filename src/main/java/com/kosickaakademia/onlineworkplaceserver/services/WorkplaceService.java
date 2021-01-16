package com.kosickaakademia.onlineworkplaceserver.services;

import com.kosickaakademia.onlineworkplaceserver.entities.UserDTO;
import com.kosickaakademia.onlineworkplaceserver.entities.UserEntity;
import com.kosickaakademia.onlineworkplaceserver.entities.WorkplaceEntity;

import java.util.List;

public interface WorkplaceService {
    List<WorkplaceEntity> getAllUserWorkplace(Long id);
    WorkplaceEntity addWorkplace(WorkplaceEntity workplaceEntity);
    void addUserToWorkplace(Long userId, Long workplaceId);
    List<UserDTO> getAllUsers(Long workplaceId);
    void deleteUser(Long userId, Long workplaceId);
}
