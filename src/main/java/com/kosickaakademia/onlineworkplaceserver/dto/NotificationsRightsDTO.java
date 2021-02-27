package com.kosickaakademia.onlineworkplaceserver.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationsRightsDTO {
    private Long id;
    private boolean addedToElement;
    private boolean sentMessage;
    private boolean dueDate;
    private boolean removedFromElement;
}
