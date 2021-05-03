package com.kosickaakademia.onlineworkplaceserver.services;

import com.kosickaakademia.onlineworkplaceserver.dto.NotificationDTO;
import com.kosickaakademia.onlineworkplaceserver.dto.UserDTO;
import com.kosickaakademia.onlineworkplaceserver.entities.NotificationEntity;
import com.kosickaakademia.onlineworkplaceserver.entities.UserEntity;
import com.kosickaakademia.onlineworkplaceserver.entities.WorkplaceEntity;
import com.kosickaakademia.onlineworkplaceserver.enums.NotificationType;
import com.kosickaakademia.onlineworkplaceserver.repositories.NotificationRepository;
import com.sun.istack.Nullable;
import lombok.val;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void addNotification(Long workplaceId, Long userId, Long recipientId, String message, @Nullable NotificationType type) {
        val workplace = new WorkplaceEntity();
        workplace.setId(workplaceId);
        val senderUser = new UserEntity();
        senderUser.setId(userId);
        val recipientUser = new UserEntity();
        recipientUser.setId(recipientId);
        val notification = new NotificationEntity();
        notification.setCreationTime(new Date());
        notification.setDescription(message);
        notification.setFresh(true);
        notification.setType(type);
        notification.setWorkplaceEntity(workplace);
        notification.setSenderUser(senderUser);
        notification.setRecipientUser(recipientUser);

        notificationRepository.save(notification);
    }

    @Override
    public List<NotificationDTO> getNotifications(Long workplaceId, Long userId) {
        val pageRequest = PageRequest.of(0, 5);
        val notifications = notificationRepository
                .getAllByWorkplaceEntityIdAndRecipientUserId(workplaceId, userId, pageRequest);
        return mapToNotificationDTO(notifications.toList());
    }

    @Override
    public List<NotificationDTO> getNewNotifications(Long workplaceId, Long userId) {
        val notifications = notificationRepository.
                getAllByFreshTrueAndWorkplaceEntityIdAndRecipientUserId(workplaceId, userId);
        return mapToNotificationDTO(notifications);
    }

    @Transactional
    @Override
    public void setNotificationToRead(Long notificationId) {
        val notification = notificationRepository.getOne(notificationId);
        notification.setFresh(false);
        notificationRepository.save(notification);
    }

    private List<NotificationDTO> mapToNotificationDTO(List<NotificationEntity> list) {
        return list.stream()
                .map(notificationEntity -> {
                    return NotificationDTO.builder()
                            .id(notificationEntity.getId())
                            .creationTime(notificationEntity.getCreationTime())
                            .description(notificationEntity.getDescription())
                            .fresh(notificationEntity.isFresh())
                            .type(notificationEntity.getType())
                            .senderUser(
                                    new UserDTO(
                                            notificationEntity.getSenderUser().getId(),
                                            notificationEntity.getSenderUser().getUserName(),
                                            notificationEntity.getSenderUser().getUserSurname(),
                                            notificationEntity.getSenderUser().getEmail(),
                                            notificationEntity.getSenderUser().getPhoto()
                                    )
                            ).build();
                })
                .collect(Collectors.toList());
    }
}

