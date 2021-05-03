package com.kosickaakademia.onlineworkplaceserver.services;

import com.kosickaakademia.onlineworkplaceserver.dto.NotificationsRightsDTO;
import com.kosickaakademia.onlineworkplaceserver.dto.RightsDTO;
import com.kosickaakademia.onlineworkplaceserver.dto.UserRightsDTO;
import com.kosickaakademia.onlineworkplaceserver.entities.NotificationRightsEntity;
import com.kosickaakademia.onlineworkplaceserver.entities.UserRightsEntity;

import java.util.List;

public interface UserRightsService {
    RightsDTO getUserRights(Long workplaceId, Long userId);
    UserRightsDTO addUserRights(Long workplaceId, Long userId, UserRightsEntity userRightsDTO);
    NotificationsRightsDTO addNotificationRights(Long workplaceId, Long userId, NotificationRightsEntity userRightsEntity);
    List<UserRightsDTO> getAllUserRights(Long workplaceId);
}
