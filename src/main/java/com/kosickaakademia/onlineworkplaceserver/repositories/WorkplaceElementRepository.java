package com.kosickaakademia.onlineworkplaceserver.repositories;

import com.kosickaakademia.onlineworkplaceserver.entities.workplaceelement.WorkplaceElementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkplaceElementRepository extends JpaRepository<WorkplaceElementEntity, Long> {
    List<WorkplaceElementEntity> findAllByWorkplaceEntityId(Long workplaceId);
    WorkplaceElementEntity findWorkplaceElementEntityBy(Long elementID);
}
