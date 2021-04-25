package com.kosickaakademia.onlineworkplaceserver.services;

import com.kosickaakademia.onlineworkplaceserver.dto.MessageDTO;
import com.kosickaakademia.onlineworkplaceserver.dto.UserDTO;
import com.kosickaakademia.onlineworkplaceserver.entities.MessageEntity;
import com.kosickaakademia.onlineworkplaceserver.entities.UserEntity;
import com.kosickaakademia.onlineworkplaceserver.entities.workplaceelement.WorkplaceElementEntity;
import com.kosickaakademia.onlineworkplaceserver.repositories.MessageRepository;
import javassist.NotFoundException;
import lombok.val;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {
    private final MessageRepository messageRepository;

    public ChatServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void addNewMessage(MessageDTO messageDTO, String threadId) {
        messageRepository.save(mapMessageDTOToEntity(messageDTO, Long.parseLong(threadId)));
    }

    @Override
    public List<MessageDTO> getMessages(Long threadId, String pageNumber) throws NotFoundException {
        val pageRequest = PageRequest.of(Integer.parseInt(pageNumber), 25);
        val page = messageRepository.getAllByThreadIdOrderByIdDesc(threadId, pageRequest);
        if (page.getTotalPages() < Integer.parseInt(pageNumber)) {
            throw new NotFoundException("Page number doesn't exists");
        }

        return page
                .stream()
                .map(this::mapMessageEntityToMessageDTO)
                .collect(Collectors.toList());
    }

    private MessageEntity mapMessageDTOToEntity(MessageDTO messageDTO, Long threadId) {
        val user = new UserEntity();
        user.setId(messageDTO.getSenderUser().getId());

        val entity = new WorkplaceElementEntity();
        entity.setId(threadId);
        return MessageEntity.builder()
                .description(messageDTO.getDescription())
                .senderUser(user)
                .thread(entity)
                .build();
    }

    private MessageDTO mapMessageEntityToMessageDTO(MessageEntity messageEntity) {
        return MessageDTO.builder()
                .description(messageEntity.getDescription())
                .senderUser(mapUserEntityToDTO(messageEntity.getSenderUser()))
                .timestamp(messageEntity.getTimestamp().toString())
                .build();
    }

    private UserDTO mapUserEntityToDTO(UserEntity senderUser) {
        return UserDTO.builder()
                .id(senderUser.getId())
                .email(senderUser.getEmail())
                .userName(senderUser.getUserName())
                .userSurname(senderUser.getUserSurname())
                .photo(senderUser.getPhoto())
                .build();
    }
}
