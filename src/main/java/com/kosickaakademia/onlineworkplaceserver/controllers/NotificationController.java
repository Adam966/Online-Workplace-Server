package com.kosickaakademia.onlineworkplaceserver.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class NotificationController {
    private static final String GET_NOTIFICATIONS = "notifications/{userId}";
    private static final String SSE_NOTIFICATION = "sse/notification/{userId}";

    @GetMapping(SSE_NOTIFICATION)
    SseEmitter streamNotifications(@PathVariable Long userId) {
        SseEmitter emitter = new SseEmitter();
        ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
        sseMvcExecutor.execute(() -> {

            // TODO add notification service and repository for notifications
            try {
                    SseEmitter.SseEventBuilder event = SseEmitter
                            .event()
                            .name("notification")
                            .id("notification id")
                            .data("Notification data")
                            .reconnectTime(10000L);

                    emitter.send(event);

            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        });
        return emitter;
    }
}
