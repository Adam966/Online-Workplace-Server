package com.kosickaakademia.onlineworkplaceserver.services;

import com.kosickaakademia.onlineworkplaceserver.dto.NotificationDTO;
import com.kosickaakademia.onlineworkplaceserver.entities.NotificationEntity;

import java.util.List;

public interface NotificationService {
    void addNotification(Long workplaceId, Long userId);
    List<NotificationDTO> getNotifications(Long workplaceId, Long userId);
    List<NotificationDTO> getNewNotifications(Long workplaceId, Long userId);
}
