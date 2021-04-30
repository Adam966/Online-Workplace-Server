package com.kosickaakademia.onlineworkplaceserver.repositories;

import com.kosickaakademia.onlineworkplaceserver.entities.NotificationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
    List<NotificationEntity> getAllByFreshTrueAndWorkplaceEntityIdAndRecipientUserId(Long workplaceId, Long userId);
    Page<NotificationEntity> getAllByWorkplaceEntityIdAndRecipientUserId(Long workplaceId, Long userId, Pageable pageRequest);
}
