package com.kosickaakademia.onlineworkplaceserver.services;

import com.kosickaakademia.onlineworkplaceserver.dto.NotificationDTO;
import com.kosickaakademia.onlineworkplaceserver.dto.UserDTO;
import com.kosickaakademia.onlineworkplaceserver.repositories.NotificationRepository;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void addNotification(Long workplaceId, Long userId) {

    }

    @Override
    public List<NotificationDTO> getNotifications(Long workplaceId, Long userId) {
        return null;
    }

    @Override
    public List<NotificationDTO> getNewNotifications(Long workplaceId, Long userId) {
        val notifications = notificationRepository.
                getAllByFreshTrueAndAndWorkplaceEntityIdAndRecipientUserId(workplaceId, userId);

        return notifications.stream()
                .map(notificationEntity -> {
                    return NotificationDTO.builder()
                            .id(notificationEntity.getId())
                            .creationTime(notificationEntity.getCreationTime())
                            .description(notificationEntity.getDescription())
                            .fresh(notificationEntity.isFresh())
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
