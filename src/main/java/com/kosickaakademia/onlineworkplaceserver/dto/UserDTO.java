package com.kosickaakademia.onlineworkplaceserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String userName;
    private String userSurname;
    private String email;
}
