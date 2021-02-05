package com.kosickaakademia.onlineworkplaceserver.repositories;

import com.kosickaakademia.onlineworkplaceserver.entities.workplaceelement.CheckListEntity;

import java.util.List;

public interface CheckListRepository extends WorkplaceElementRepository<CheckListEntity> {
    List<CheckListEntity> getAllByWorkplaceEntityIdAndArchivedLike(Long id, boolean isArchive);
}
