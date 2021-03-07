package com.kosickaakademia.onlineworkplaceserver.controllers;

import com.kosickaakademia.onlineworkplaceserver.dto.MessageDTO;
import com.kosickaakademia.onlineworkplaceserver.services.ChatServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ChatController {
    private static final String GET_OLD_MESSAGES = "thread/{threadId}";
    private final ChatServiceImpl chatService;

    public ChatController(ChatServiceImpl chatService) {
        this.chatService = chatService;
    }

    @MessageMapping("/message/{threadId}")
    @SendTo("/message/thread/{threadId}")
    public MessageDTO getNewMessage(@Payload MessageDTO message, @DestinationVariable String threadId) {
        chatService.addNewMessage(message, threadId);
        return message;
    }

    @GetMapping(GET_OLD_MESSAGES)
    public ResponseEntity<List<MessageDTO>> getOldMessages(@PathVariable String threadId) {
        return ResponseEntity.ok(chatService.getMessages(Long.parseLong(threadId)));
    }
}
