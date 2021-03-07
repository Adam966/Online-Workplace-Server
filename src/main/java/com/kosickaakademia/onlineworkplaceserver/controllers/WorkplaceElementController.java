package com.kosickaakademia.onlineworkplaceserver.controllers;

import com.kosickaakademia.onlineworkplaceserver.entities.workplaceelement.WorkplaceElementEntity;
import com.kosickaakademia.onlineworkplaceserver.services.WorkplaceElementServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WorkplaceElementController {
    private static final String GET_ALL_WORKPLACE_ELEMENTS = "workplace/{workplaceId}/elements";
    private static final String WORKPLACE_ELEMENT = "workplace/{workplaceId}/element";
    private static final String ARCHIVE_ELEMENT = "archive-element/{elementId}";
    private static final String GET_ALL_ARCHIVED_ELEMENTS = "workplace/{workplaceId}/archived-elements";

    private final WorkplaceElementServiceImpl workplaceElementService;

    public WorkplaceElementController(WorkplaceElementServiceImpl workplaceElementService) {
        this.workplaceElementService = workplaceElementService;
    }

    @GetMapping(GET_ALL_WORKPLACE_ELEMENTS)
    ResponseEntity<List<WorkplaceElementEntity>> getAllWorkplaceWElements(@PathVariable Long workplaceId) {
        return ResponseEntity.ok(workplaceElementService.getAllElements(workplaceId));
    }

    @PutMapping(WORKPLACE_ELEMENT)
    ResponseEntity<WorkplaceElementEntity> addElementToWorkplace(@RequestBody WorkplaceElementEntity elementEntity, @PathVariable Long workplaceId) {
        return ResponseEntity.ok(workplaceElementService.addElement(elementEntity, workplaceId));
    }

    @PutMapping(ARCHIVE_ELEMENT)
    void archiveElement(@PathVariable Long elementId) {
        workplaceElementService.archiveElement(elementId);
    }

    @GetMapping(GET_ALL_ARCHIVED_ELEMENTS)
    ResponseEntity<List<WorkplaceElementEntity>> getAllWorkplaceArchivedElements(@PathVariable Long workplaceId) {
        return ResponseEntity.ok(workplaceElementService.getAllArchivedElements(workplaceId));
    }
}
