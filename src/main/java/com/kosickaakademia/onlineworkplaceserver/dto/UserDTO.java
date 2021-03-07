package com.kosickaakademia.onlineworkplaceserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String userName;
    private String userSurname;
    private String email;
    private Long photo;
}
