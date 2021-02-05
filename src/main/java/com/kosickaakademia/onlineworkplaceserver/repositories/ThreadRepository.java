package com.kosickaakademia.onlineworkplaceserver.repositories;

import com.kosickaakademia.onlineworkplaceserver.entities.workplaceelement.ThreadEntity;

import java.util.List;

public interface ThreadRepository extends WorkplaceElementRepository<ThreadEntity> {
    List<ThreadEntity> getAllByWorkplaceEntityIdAndArchivedLike(Long id, boolean isArchived);

}
