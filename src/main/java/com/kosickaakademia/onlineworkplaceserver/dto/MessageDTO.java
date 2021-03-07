package com.kosickaakademia.onlineworkplaceserver.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageDTO {
    String description;
    String timestamp;
    UserDTO senderUser;
}
