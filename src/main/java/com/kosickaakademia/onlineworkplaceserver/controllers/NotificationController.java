package com.kosickaakademia.onlineworkplaceserver.controllers;

import com.kosickaakademia.onlineworkplaceserver.dto.NotificationDTO;
import com.kosickaakademia.onlineworkplaceserver.dto.UserDTO;
import com.kosickaakademia.onlineworkplaceserver.enums.NotificationType;
import com.kosickaakademia.onlineworkplaceserver.services.NotificationServiceImpl;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class NotificationController {
    private static final String GET_NOTIFICATIONS = "notifications/user/{userId}/workplace/{workplaceId}";
    private static final String SSE_NOTIFICATION = "sse/notification/user/{userId}/workplace/{workplaceId}";

    private final NotificationServiceImpl notificationService;

    public NotificationController(NotificationServiceImpl notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping(SSE_NOTIFICATION)
    SseEmitter streamNotifications(@PathVariable Long userId, @PathVariable Long workplaceId) {
        SseEmitter emitter = new SseEmitter();
        ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
        val notifications = notificationService.getNewNotifications(workplaceId, userId);
        //val notifications = setDummyData();

        sseMvcExecutor.execute(() -> {
            try {
                for (NotificationDTO notification: notifications) {
                    SseEmitter.SseEventBuilder event = SseEmitter
                            .event()
                            .comment(notification.getType().name())
                            .name("notification")
                            .data(notification)
                            .reconnectTime(1000L * 60);

                    emitter.send(event);
                    Thread.sleep(3000L);
                }
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        });
        return emitter;
    }

    @GetMapping(GET_NOTIFICATIONS)
    ResponseEntity<List<NotificationDTO>> getAllNotifications(@PathVariable Long userId, @PathVariable Long workplaceId) {
        return ResponseEntity.ok(notificationService.getNotifications(workplaceId, userId));
    }

    private ArrayList<NotificationDTO> setDummyData() {
        val list = new ArrayList<NotificationDTO>();
        for (long i = 0; i < 15; i++) {
            val notification = NotificationDTO.builder()
                    .id(i)
                    .creationTime(new Date())
                    .description("Adam just added you to element")
                    .fresh(true)
                    .type(NotificationType.GENERAL_INFO)
                    .senderUser(
                            new UserDTO(
                                    1L,
                                    "Adam",
                                    "Ivan",
                                   "adam.ivan@kosickaakademia.sk",
                                    null
                            )
                    ).build();

            list.add(notification);
        }
        return list;
    }
}
