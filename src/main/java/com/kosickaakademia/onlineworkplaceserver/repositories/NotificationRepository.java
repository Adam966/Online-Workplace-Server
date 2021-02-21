package com.kosickaakademia.onlineworkplaceserver.repositories;

import com.kosickaakademia.onlineworkplaceserver.entities.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
    List<NotificationEntity> getAllByFreshTrueAndAndWorkplaceEntityIdAndRecipientUserId(Long workplaceId, Long userId);
    List<NotificationEntity> getAllByWorkplaceEntityIdAndRecipientUserId(Long workplaceId, Long userId);
}
