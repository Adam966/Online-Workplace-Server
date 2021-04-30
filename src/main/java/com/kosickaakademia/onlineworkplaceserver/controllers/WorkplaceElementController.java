package com.kosickaakademia.onlineworkplaceserver.controllers;

import com.kosickaakademia.onlineworkplaceserver.entities.workplaceelement.WorkplaceElementEntity;
import com.kosickaakademia.onlineworkplaceserver.services.WorkplaceElementServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WorkplaceElementController {
    private static final String GET_ALL_WORKPLACE_ELEMENTS = "workplace/{workplaceId}/elements";
    private static final String WORKPLACE_ELEMENT = "workplace/{workplaceId}/element";
    private static final String ARCHIVE_ELEMENT = "workplace/{workplaceId}/archive-element/{elementId}";
    private static final String GET_ALL_ARCHIVED_ELEMENTS = "workplace/{workplaceId}/archived-elements";

    private final WorkplaceElementServiceImpl workplaceElementService;

    public WorkplaceElementController(WorkplaceElementServiceImpl workplaceElementService) {
        this.workplaceElementService = workplaceElementService;
    }

    @PreAuthorize("@securityService.isUserInWorkplace(#workplaceId)")
    @GetMapping(GET_ALL_WORKPLACE_ELEMENTS)
    ResponseEntity<List<WorkplaceElementEntity>> getAllWorkplaceWElements(@PathVariable Long workplaceId) {
        return ResponseEntity.ok(workplaceElementService.getAllElements(workplaceId));
    }

    @PreAuthorize("@securityService.isUserInWorkplace(#workplaceId)")
    @PutMapping(WORKPLACE_ELEMENT)
    ResponseEntity<WorkplaceElementEntity> addElementToWorkplace(@RequestBody WorkplaceElementEntity elementEntity, @PathVariable Long workplaceId) {
        return ResponseEntity.ok(workplaceElementService.addElement(elementEntity, workplaceId));
    }

    @PreAuthorize("@securityService.checkUserRightToArchive(#workplaceId)")
    @PutMapping(ARCHIVE_ELEMENT)
    void archiveElement(@PathVariable Long workplaceId, @PathVariable Long elementId) {
        workplaceElementService.archiveElement(elementId);
    }

    @PreAuthorize("@securityService.isUserInWorkplace(#workplaceId)")
    @GetMapping(GET_ALL_ARCHIVED_ELEMENTS)
    ResponseEntity<List<WorkplaceElementEntity>> getAllWorkplaceArchivedElements(@PathVariable Long workplaceId) {
        return ResponseEntity.ok(workplaceElementService.getAllArchivedElements(workplaceId));
    }
}
