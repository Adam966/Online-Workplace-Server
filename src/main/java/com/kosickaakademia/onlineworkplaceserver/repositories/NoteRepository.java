package com.kosickaakademia.onlineworkplaceserver.repositories;

import com.kosickaakademia.onlineworkplaceserver.entities.workplaceelement.NoteEntity;

import java.util.List;

public interface NoteRepository extends WorkplaceElementRepository<NoteEntity> {
    List<NoteEntity> getAllByWorkplaceEntityIdAndArchivedLike(Long id, boolean isArchived);
}
