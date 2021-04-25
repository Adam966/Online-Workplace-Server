package com.kosickaakademia.onlineworkplaceserver.services;

import com.kosickaakademia.onlineworkplaceserver.dto.MessageDTO;
import javassist.NotFoundException;

import java.util.List;

public interface ChatService {
    void addNewMessage(MessageDTO messageDTO, String threadId);
    List<MessageDTO> getMessages(Long threadId, String pageNumber) throws NotFoundException;
}
