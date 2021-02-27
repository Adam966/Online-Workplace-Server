package com.kosickaakademia.onlineworkplaceserver.repositories;

import com.kosickaakademia.onlineworkplaceserver.entities.UserRightsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRightsRepository extends JpaRepository<UserRightsEntity, Long> {
    UserRightsEntity getUserRightsEntityByWorkplaceEntityIdAndUserEntityId(Long workplaceId, Long userId);
}
