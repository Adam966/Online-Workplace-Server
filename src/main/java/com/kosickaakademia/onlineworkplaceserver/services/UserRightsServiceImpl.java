package com.kosickaakademia.onlineworkplaceserver.services;

import com.kosickaakademia.onlineworkplaceserver.dto.UserRightsDTO;
import com.kosickaakademia.onlineworkplaceserver.entities.UserEntity;
import com.kosickaakademia.onlineworkplaceserver.entities.UserRightsEntity;
import com.kosickaakademia.onlineworkplaceserver.entities.WorkplaceEntity;
import com.kosickaakademia.onlineworkplaceserver.repositories.UserRightsRepository;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
public class UserRightsServiceImpl implements UserRightsService {
    private final UserRightsRepository userRightsRepository;

    public UserRightsServiceImpl(UserRightsRepository userRightsRepository) {
        this.userRightsRepository = userRightsRepository;
    }

    @Override
    public UserRightsDTO getUserRights(Long workplaceId, Long userId) {
        val rights = userRightsRepository.getUserRightsEntityByWorkplaceEntityIdAndUserEntityId(workplaceId, userId);
        return mapRightsEntityToDTO(rights);
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

    private UserRightsDTO mapRightsEntityToDTO(UserRightsEntity userRightsEntity) {
        return UserRightsDTO.builder()
                .id(userRightsEntity.getId())
                .changeRights(userRightsEntity.isChangeRights())
                .removeFromWorkplace(userRightsEntity.isRemoveFromWorkplace())
                .archiveElement(userRightsEntity.isArchiveElement())
                .addToWorkplace(userRightsEntity.isAddToWorkplace())
                .build();

    }
}
