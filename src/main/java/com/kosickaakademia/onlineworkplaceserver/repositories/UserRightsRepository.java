package com.kosickaakademia.onlineworkplaceserver.repositories;

import com.kosickaakademia.onlineworkplaceserver.entities.UserRightsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRightsRepository extends JpaRepository<UserRightsEntity, Long> {
    UserRightsEntity getUserRightsEntityByWorkplaceEntityIdAndUserEntityId(Long workplaceId, Long userId);
    List<UserRightsEntity> getAllByWorkplaceEntityId(Long workplaceId);
}
