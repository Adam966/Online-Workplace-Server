package com.kosickaakademia.onlineworkplaceserver.dto;
import com.kosickaakademia.onlineworkplaceserver.enums.NotificationType;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class NotificationDTO {
    private Long id;

    private NotificationType type;
    private String description;
    private boolean fresh;
    private Date creationTime;
    private UserDTO senderUser;
}
