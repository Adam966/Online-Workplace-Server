package com.kosickaakademia.onlineworkplaceserver.services;

import com.kosickaakademia.onlineworkplaceserver.entities.workplaceelement.WorkplaceElementEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkplaceElementServiceImpl implements WorkplaceElementService {
    @Override
    public List<WorkplaceElementEntity> getAllElements(Long workplaceId) {
        return null;
    }

    @Override
    public WorkplaceElementEntity addElement(WorkplaceElementEntity workplaceElementEntity) {
        return null;
    }

    @Override
    public void updateElement(WorkplaceElementEntity elementEntity) {

    }

    @Override
    public void archiveElement(Long elementId) {

    }
}
