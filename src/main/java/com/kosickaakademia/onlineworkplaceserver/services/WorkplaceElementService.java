package com.kosickaakademia.onlineworkplaceserver.services;

import com.kosickaakademia.onlineworkplaceserver.entities.workplaceelement.WorkplaceElementEntity;

import java.util.List;

public interface WorkplaceElementService {
    List<WorkplaceElementEntity> getAllElements(Long workplaceId);
    WorkplaceElementEntity addElement(WorkplaceElementEntity workplaceElementEntity);
    void archiveElement(Long workplaceId, WorkplaceElementEntity workplaceElementEntity);
    List<WorkplaceElementEntity> getAllArchivedElements(Long workplaceId);
}
