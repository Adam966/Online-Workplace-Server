package com.kosickaakademia.onlineworkplaceserver.controllers;

import com.kosickaakademia.onlineworkplaceserver.entities.LabelEntity;
import com.kosickaakademia.onlineworkplaceserver.dto.UserDTO;
import com.kosickaakademia.onlineworkplaceserver.entities.WorkplaceEntity;
import com.kosickaakademia.onlineworkplaceserver.exceptions.DuplicateUserException;
import com.kosickaakademia.onlineworkplaceserver.services.WorkplaceServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController()
public class WorkplaceController {
    private static final String WORKPLACE = "workplace";
    private static final String WORKPLACES = "workplaces";

    private static final String ADD_WORKPLACE_USER = "workplace/{workplaceId}/user/{userId}";
    private static final String DELETE_WORKPLACE_USER = "workplace/{workplaceId}/user/{userId}";
    private static final String GET_WORKPLACE_USERS = "workplace/{workplaceId}/users";

    private static final String ADD_WORKPLACE_LABEL = "workplace/{workplaceId}/label";
    private static final String DELETE_WORKPLACE_LABEL = "workplace/{workplaceId}/label/{labelId}";
    private static final String GET_WORKPLACE_LABELS = "workplace/{workplaceId}/labels";

    private final WorkplaceServiceImpl workplaceService;

    public WorkplaceController(WorkplaceServiceImpl workplaceService) {
        this.workplaceService = workplaceService;
    }

    @GetMapping(WORKPLACES)
    ResponseEntity<List<WorkplaceEntity>> getAllWorkplaces(@RequestParam(name = "userId") Long id, Principal principal) {
        if (id.toString().equals(principal.getName())) {
            return ResponseEntity.ok(workplaceService.getAllUserWorkplace(id));
        } else {
            throw new AccessDeniedException("You don't have right to see this.");
        }
    }

    @PostMapping(WORKPLACE)
    ResponseEntity<WorkplaceEntity> addWorkplace(@RequestBody WorkplaceEntity workplaceEntity) {
        return ResponseEntity.ok(workplaceService.addWorkplace(workplaceEntity));
    }

    @DeleteMapping(DELETE_WORKPLACE_USER)
    void deleteUserFromWorkplace(@PathVariable Long workplaceId, @PathVariable Long userId) {
        workplaceService.deleteUser(userId, workplaceId);
    }

    @PostMapping(ADD_WORKPLACE_USER)
    void addUserToWorkplace(@PathVariable Long workplaceId, @PathVariable Long userId) {
        try {
            workplaceService.addUserToWorkplace(userId, workplaceId);
        } catch (Exception e) {
            throw new DuplicateUserException();
        }
    }

    @GetMapping(GET_WORKPLACE_USERS)
    ResponseEntity<List<UserDTO>> getAllWorkplaceUsers(@PathVariable Long workplaceId) {
        return ResponseEntity.ok(workplaceService.getAllUsers(workplaceId));
    }

    @GetMapping(GET_WORKPLACE_LABELS)
    ResponseEntity<List<LabelEntity>> getAllWorkplaceLabels(@PathVariable Long workplaceId) {
        return ResponseEntity.ok(workplaceService.getAllLabels(workplaceId));
    }

    @PostMapping(ADD_WORKPLACE_LABEL)
    ResponseEntity<LabelEntity> addLabelToWorkplace(@RequestBody LabelEntity labelEntity, @PathVariable Long workplaceId) {
        return ResponseEntity.ok(workplaceService.addLabel(labelEntity, workplaceId));
    }

    @DeleteMapping(DELETE_WORKPLACE_LABEL)
    void deleteLabelFromWorkplace(@PathVariable Long workplaceId, @PathVariable Long labelId) {
        workplaceService.deleteLabel(workplaceId, labelId);
    }
}
