package com.kosickaakademia.onlineworkplaceserver.controllers;

import com.kosickaakademia.onlineworkplaceserver.entities.UserDTO;
import com.kosickaakademia.onlineworkplaceserver.entities.WorkplaceEntity;
import com.kosickaakademia.onlineworkplaceserver.services.WorkplaceServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class WorkplaceController {
    private static final String WORKPLACE = "/workplace";
    private static final String WORKPLACES = "/workplaces";
    private static final String ADD_USER = "workplace/{workplaceId}/user/{userId}";
    private static final String GET_WORKPLACE_USERS = "workplace/{workplaceId}/users";
    private static final String DELETE_WORKPLACE_USER = "workplace/{workplaceId}/user/{userId}";

    private final WorkplaceServiceImpl workplaceService;

    public WorkplaceController(WorkplaceServiceImpl workplaceService) {
        this.workplaceService = workplaceService;
    }

    @GetMapping(WORKPLACES)
    ResponseEntity<List<WorkplaceEntity>> getAllWorkplaces(@RequestParam(name = "userId") Long id) {
        return ResponseEntity.ok(workplaceService.getAllUserWorkplace(id));
    }

    @PostMapping(WORKPLACE)
    ResponseEntity<WorkplaceEntity> addWorkplace(@RequestBody WorkplaceEntity workplaceEntity) {
        return ResponseEntity.ok(workplaceService.addWorkplace(workplaceEntity));
    }

    @DeleteMapping(DELETE_WORKPLACE_USER)
    void deleteUserFromWorkplace(@PathVariable Long workplaceId, @PathVariable Long userId) {
        workplaceService.deleteUser(userId, workplaceId);
    }

    @PostMapping(ADD_USER)
    void addUserToWorkplace(@PathVariable Long workplaceId, @PathVariable Long userId) {
        workplaceService.addUserToWorkplace(userId, workplaceId);
    }

    @GetMapping(GET_WORKPLACE_USERS)
    ResponseEntity<List<UserDTO>> getAllWorkplaceUsers(@PathVariable Long workplaceId) {
        return ResponseEntity.ok(workplaceService.getAllUsers(workplaceId));
    }
}
