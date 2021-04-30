package com.kosickaakademia.onlineworkplaceserver.services;

import com.kosickaakademia.onlineworkplaceserver.dto.NotificationDTO;
import com.kosickaakademia.onlineworkplaceserver.entities.NotificationEntity;
import com.kosickaakademia.onlineworkplaceserver.enums.NotificationType;
import com.sun.istack.Nullable;

import java.util.List;

public interface NotificationService {
    void addNotification(Long workplaceId, Long userId, Long recipientId, String message, @Nullable NotificationType type);
    List<NotificationDTO> getNotifications(Long workplaceId, Long userId);
    List<NotificationDTO> getNewNotifications(Long workplaceId, Long userId);
    void setNotificationToRead(Long notificationId);
}
