package com.kosickaakademia.onlineworkplaceserver.controllers;

import com.kosickaakademia.onlineworkplaceserver.entities.workplaceelement.NoteEntity;
import com.kosickaakademia.onlineworkplaceserver.entities.workplaceelement.WorkplaceElementEntity;
import com.kosickaakademia.onlineworkplaceserver.services.WorkplaceElementService;
import com.kosickaakademia.onlineworkplaceserver.services.WorkplaceElementServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WorkplaceElementController {
    private static final String GET_ALL_WORKPLACE_ELEMENTS = "workplace/{workplaceId}/elements";
    private static final String WORKPLACE_ELEMENT = "workplace-element";

    private final WorkplaceElementServiceImpl workplaceElementService;

    public WorkplaceElementController(WorkplaceElementServiceImpl workplaceElementService) {
        this.workplaceElementService = workplaceElementService;
    }

    @GetMapping(GET_ALL_WORKPLACE_ELEMENTS)
    ResponseEntity<List<WorkplaceElementEntity>> getAllWorkplaceWElements(@PathVariable Long workplaceId) {
        return ResponseEntity.ok(workplaceElementService.getAllElements(workplaceId));
    }

    @PostMapping(WORKPLACE_ELEMENT)
    ResponseEntity<WorkplaceElementEntity> addElementToWorkplace(@RequestBody WorkplaceElementEntity elementEntity) {
        return ResponseEntity.ok(workplaceElementService.addElement(elementEntity));
    }

    @PutMapping(WORKPLACE_ELEMENT)
    void updateElementInWorkplace(@RequestBody WorkplaceElementEntity elementEntity) {
        workplaceElementService.updateElement(elementEntity);
    }
}
