package com.kosickaakademia.onlineworkplaceserver.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String userName;
    private String userSurname;
    private String email;
}
