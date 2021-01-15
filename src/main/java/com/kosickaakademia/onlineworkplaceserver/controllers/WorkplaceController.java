package com.kosickaakademia.onlineworkplaceserver.controllers;

import com.kosickaakademia.onlineworkplaceserver.entities.WorkplaceEntity;
import com.kosickaakademia.onlineworkplaceserver.services.WorkplaceServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WorkplaceController {
    private final WorkplaceServiceImpl workplaceService;

    public WorkplaceController(WorkplaceServiceImpl workplaceService) {
        this.workplaceService = workplaceService;
    }

    @GetMapping("/workplaces")
    List<WorkplaceEntity> getAllWorkplaces(@RequestParam(name = "userId") Long id) {
        return workplaceService.getAllUserWorkplace(id);
    }

    @PutMapping("/add-workplace")
    void addWorkplace(@RequestBody WorkplaceEntity workplaceEntity) {
        workplaceService.addWorkplace(workplaceEntity);
    }

    @PostMapping("/workplace/{workplaceId}/add-user/{userId}")
    void addUserToWorkplace(@PathVariable Long workplaceId, @PathVariable Long userId) {
        workplaceService.addUserToWorkplace(userId, workplaceId);
    }
}
