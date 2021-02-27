package com.kosickaakademia.onlineworkplaceserver.services;

import com.kosickaakademia.onlineworkplaceserver.dto.UserRightsDTO;
import com.kosickaakademia.onlineworkplaceserver.entities.UserRightsEntity;

public interface UserRightsService {
    UserRightsDTO getUserRights(Long workplaceId, Long userId);
    UserRightsDTO addUserRights(Long workplaceId, Long userId, UserRightsEntity userRightsDTO);
}
