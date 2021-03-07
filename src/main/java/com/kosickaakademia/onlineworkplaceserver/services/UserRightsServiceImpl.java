package com.kosickaakademia.onlineworkplaceserver.services;

import com.kosickaakademia.onlineworkplaceserver.dto.NotificationsRightsDTO;
import com.kosickaakademia.onlineworkplaceserver.dto.RightsDTO;
import com.kosickaakademia.onlineworkplaceserver.dto.UserRightsDTO;
import com.kosickaakademia.onlineworkplaceserver.entities.NotificationRightsEntity;
import com.kosickaakademia.onlineworkplaceserver.entities.UserEntity;
import com.kosickaakademia.onlineworkplaceserver.entities.UserRightsEntity;
import com.kosickaakademia.onlineworkplaceserver.entities.WorkplaceEntity;
import com.kosickaakademia.onlineworkplaceserver.repositories.NotificationsRightsRepository;
import com.kosickaakademia.onlineworkplaceserver.repositories.UserRightsRepository;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
public class UserRightsServiceImpl implements UserRightsService {
    private final UserRightsRepository userRightsRepository;
    private final NotificationsRightsRepository notificationsRightsRepository;

    public UserRightsServiceImpl(UserRightsRepository userRightsRepository, NotificationsRightsRepository notificationsRightsRepository) {
        this.userRightsRepository = userRightsRepository;
        this.notificationsRightsRepository = notificationsRightsRepository;
    }

    @Override
    public RightsDTO getUserRights(Long workplaceId, Long userId) {
        val userRights = userRightsRepository.getUserRightsEntityByWorkplaceEntityIdAndUserEntityId(workplaceId, userId);
        val notificationRights = notificationsRightsRepository.getNotificationRightsEntityByWorkplaceEntityIdAndUserEntityId(workplaceId, userId);
        val rights = new RightsDTO();
        rights.setUserRights(mapRightsEntityToDTO(userRights));
        rights.setNotificationsRights(mapNotificationsRightsEntityToDTO(notificationRights));
        return rights;
    }

    @Override
    public UserRightsDTO addUserRights(Long workplaceId, Long userId, UserRightsEntity userRightsEntity) {
        val workplace = new WorkplaceEntity();
        workplace.setId(workplaceId);

        val userEntity = new UserEntity();
        userEntity.setId(userId);

        userRightsEntity.setWorkplaceEntity(workplace);
        userRightsEntity.setUserEntity(userEntity);
        return mapRightsEntityToDTO(userRightsRepository.save(userRightsEntity));
    }

    @Override
    public NotificationsRightsDTO addNotificationRights(Long workplaceId, Long userId, NotificationRightsEntity notificationRightsEntity) {
        val workplace = new WorkplaceEntity();
        workplace.setId(workplaceId);

        val userEntity = new UserEntity();
        userEntity.setId(userId);

        notificationRightsEntity.setWorkplaceEntity(workplace);
        notificationRightsEntity.setUserEntity(userEntity);
        return mapNotificationsRightsEntityToDTO(notificationsRightsRepository.save(notificationRightsEntity));
    }

    private UserRightsDTO mapRightsEntityToDTO(UserRightsEntity userRightsEntity) {
        return UserRightsDTO.builder()
                .id(userRightsEntity.getId())
                .changeRights(userRightsEntity.isChangeRights())
                .removeFromWorkplace(userRightsEntity.isRemoveFromWorkplace())
                .archiveElement(userRightsEntity.isArchiveElement())
                .addToWorkplace(userRightsEntity.isAddToWorkplace())
                .build();

    }

    private NotificationsRightsDTO mapNotificationsRightsEntityToDTO(NotificationRightsEntity notificationRightsEntity) {
        return NotificationsRightsDTO.builder()
                .id(notificationRightsEntity.getId())
                .addedToElement(notificationRightsEntity.isAddedToElement())
                .dueDate(notificationRightsEntity.isDueDate())
                .removedFromElement(notificationRightsEntity.isRemovedFromElement())
                .sentMessage(notificationRightsEntity.isSentMessage())
                .build();
    }
}
