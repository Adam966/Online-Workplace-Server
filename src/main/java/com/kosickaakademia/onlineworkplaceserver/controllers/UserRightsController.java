package com.kosickaakademia.onlineworkplaceserver.controllers;

import com.kosickaakademia.onlineworkplaceserver.dto.UserRightsDTO;
import com.kosickaakademia.onlineworkplaceserver.entities.UserRightsEntity;
import com.kosickaakademia.onlineworkplaceserver.services.UserRightsServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserRightsController {
    private static final String USER_RIGHTS = "workplace/{workplaceId}/user/{userId}/user-rights";

    private final UserRightsServiceImpl userRightsService;

    public UserRightsController(UserRightsServiceImpl userRightsService) {
        this.userRightsService = userRightsService;
    }

    @GetMapping(USER_RIGHTS)
    private ResponseEntity<UserRightsDTO> getUserRights(@PathVariable Long workplaceId, @PathVariable Long userId) {
        return ResponseEntity.ok(userRightsService.getUserRights(workplaceId, userId));
    }

    @PutMapping(USER_RIGHTS)
    private ResponseEntity<UserRightsDTO> addUserRights(@PathVariable Long workplaceId, @PathVariable Long userId, @RequestBody  UserRightsEntity userRightsDTO) {
        return ResponseEntity.ok(userRightsService.addUserRights(workplaceId, userId, userRightsDTO));
    }
}
