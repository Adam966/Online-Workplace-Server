package com.kosickaakademia.onlineworkplaceserver.services;

import com.kosickaakademia.onlineworkplaceserver.entities.LabelEntity;
import com.kosickaakademia.onlineworkplaceserver.dto.UserDTO;
import com.kosickaakademia.onlineworkplaceserver.entities.WorkplaceEntity;

import java.util.List;

public interface WorkplaceService {
    List<WorkplaceEntity> getAllUserWorkplace(Long id);
    WorkplaceEntity addWorkplace(WorkplaceEntity workplaceEntity);
    void addUserToWorkplace(Long userId, Long workplaceId);
    List<UserDTO> getAllUsers(Long workplaceId);
    void deleteUser(Long userId, Long workplaceId);
    List<LabelEntity> getAllLabels(Long workplaceId);

    LabelEntity addLabel(LabelEntity labelEntity, Long workplaceId);
    void deleteLabel(Long workplaceId, Long labelId);
}
