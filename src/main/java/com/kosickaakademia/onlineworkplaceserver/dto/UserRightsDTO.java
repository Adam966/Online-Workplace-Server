package com.kosickaakademia.onlineworkplaceserver.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRightsDTO {
    private Long id;
    private boolean removeFromWorkplace;
    private boolean addToWorkplace;
    private boolean archiveElement;
    private boolean changeRights;
    private Long userId;
}
