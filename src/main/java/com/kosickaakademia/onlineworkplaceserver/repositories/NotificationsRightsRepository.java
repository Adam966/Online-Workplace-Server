package com.kosickaakademia.onlineworkplaceserver.repositories;

import com.kosickaakademia.onlineworkplaceserver.entities.NotificationRightsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationsRightsRepository extends JpaRepository<NotificationRightsEntity, Long> {
    NotificationRightsEntity getNotificationRightsEntityByWorkplaceEntityIdAndUserEntityId(Long workplaceId, Long userId);
}
