package com.kosickaakademia.onlineworkplaceserver.controllers;

import com.kosickaakademia.onlineworkplaceserver.dto.NotificationsRightsDTO;
import com.kosickaakademia.onlineworkplaceserver.dto.RightsDTO;
import com.kosickaakademia.onlineworkplaceserver.dto.UserRightsDTO;
import com.kosickaakademia.onlineworkplaceserver.entities.NotificationRightsEntity;
import com.kosickaakademia.onlineworkplaceserver.entities.UserRightsEntity;
import com.kosickaakademia.onlineworkplaceserver.services.UserRightsServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class RightsController {
    private static final String USER_RIGHTS = "workplace/{workplaceId}/user/{userId}/rights";
    private static final String ADD_USER_RIGHTS = "workplace/{workplaceId}/user/{userId}/user-rights";
    private static final String ADD_NOTIFICATION_RIGHTS = "workplace/{workplaceId}/user/{userId}/notification-rights";

    private final UserRightsServiceImpl userRightsService;

    public RightsController(UserRightsServiceImpl userRightsService) {
        this.userRightsService = userRightsService;
    }

    @PreAuthorize("@securityService.isUserInWorkplace(#workplaceId)")
    @GetMapping(USER_RIGHTS)
    private ResponseEntity<RightsDTO> getUserRights(@PathVariable Long workplaceId, @PathVariable Long userId) {
        return ResponseEntity.ok(userRightsService.getUserRights(workplaceId, userId));
    }

    @PutMapping(ADD_USER_RIGHTS)
    private ResponseEntity<UserRightsDTO> addUserRights(@PathVariable Long workplaceId, @PathVariable Long userId, @RequestBody  UserRightsEntity userRightsEntity) {
        return ResponseEntity.ok(userRightsService.addUserRights(workplaceId, userId, userRightsEntity));
    }

    @PreAuthorize("@securityService.isUserSameAsRequested(#workplaceId)")
    @PutMapping(ADD_NOTIFICATION_RIGHTS)
    private ResponseEntity<NotificationsRightsDTO> addNotificationRights(@PathVariable Long workplaceId, @PathVariable Long userId, @RequestBody NotificationRightsEntity notificationRightsEntity) {
        return ResponseEntity.ok(userRightsService.addNotificationRights(workplaceId, userId, notificationRightsEntity));
    }
}
