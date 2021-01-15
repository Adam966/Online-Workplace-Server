package com.kosickaakademia.onlineworkplaceserver.services;

import com.kosickaakademia.onlineworkplaceserver.entities.WorkplaceEntity;

import java.util.List;

public interface WorkplaceService {
    List<WorkplaceEntity> getAllUserWorkplace(Long id);
    void addWorkplace(WorkplaceEntity workplaceEntity);
    void deleteWorkplace();
    void addUserToWorkplace(Long userId, Long workplaceId);
}
