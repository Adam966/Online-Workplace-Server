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

import java.util.List;

@RestController
public class RightsController {
    private static final String USER_RIGHTS = "workplace/{workplaceId}/user/{userId}/rights";
    private static final String ADD_USER_RIGHTS = "workplace/{workplaceId}/user/{userId}/user-rights";
    private static final String ADD_NOTIFICATION_RIGHTS = "workplace/{workplaceId}/user/{userId}/notification-rights";
    private static final String GET_USERS_RIGHTS_OF_WORKPLACE = "workplace/{workplaceId}/users-rights";

    private final UserRightsServiceImpl userRightsService;

    public RightsController(UserRightsServiceImpl userRightsService) {
        this.userRightsService = userRightsService;
    }

    @PreAuthorize("@securityService.isUserSameAsRequested(#userId)")
    @GetMapping(USER_RIGHTS)
    public ResponseEntity<RightsDTO> getUserRights(@PathVariable Long workplaceId, @PathVariable Long userId) {
        return ResponseEntity.ok(userRightsService.getUserRights(workplaceId, userId));
    }

    @PreAuthorize("@securityService.checkUserRightToChangeRights(#workplaceId)")
    @PutMapping(ADD_USER_RIGHTS)
    public ResponseEntity<UserRightsDTO> addUserRights(@PathVariable Long workplaceId, @PathVariable Long userId, @RequestBody  UserRightsEntity userRightsEntity) {
        return ResponseEntity.ok(userRightsService.addUserRights(workplaceId, userId, userRightsEntity));
    }

    @PreAuthorize("@securityService.isUserSameAsRequested(#workplaceId)")
    @PutMapping(ADD_NOTIFICATION_RIGHTS)
    public ResponseEntity<NotificationsRightsDTO> addNotificationRights(@PathVariable Long workplaceId, @PathVariable Long userId, @RequestBody NotificationRightsEntity notificationRightsEntity) {
        return ResponseEntity.ok(userRightsService.addNotificationRights(workplaceId, userId, notificationRightsEntity));
    }

    @GetMapping(GET_USERS_RIGHTS_OF_WORKPLACE)
    public ResponseEntity<List<UserRightsDTO>> getUserRightsOfWorkplace(@PathVariable Long workplaceId) {
        return ResponseEntity.ok(userRightsService.getAllUserRights(workplaceId));
    }
}
