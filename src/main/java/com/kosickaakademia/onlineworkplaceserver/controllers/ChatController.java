package com.kosickaakademia.onlineworkplaceserver.controllers;

import com.kosickaakademia.onlineworkplaceserver.dto.MessageDTO;
import com.kosickaakademia.onlineworkplaceserver.services.ChatServiceImpl;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

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

    @MessageMapping("/notify/{threadId}")
    @SendTo("/notify/thread/{threadId}")
    public boolean getNotifyTyping(@Payload boolean isTyping, @DestinationVariable String threadId) {
        return isTyping;
    }

    @GetMapping(GET_OLD_MESSAGES)
    public ResponseEntity<List<MessageDTO>> getOldMessages(@PathVariable String threadId, @RequestParam String page) {
        try {
            return ResponseEntity.ok(chatService.getMessages(Long.parseLong(threadId), page));
        } catch (NotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }
}
