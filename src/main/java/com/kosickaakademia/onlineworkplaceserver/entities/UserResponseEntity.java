package com.kosickaakademia.onlineworkplaceserver.entities;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Data
public class UserResponseEntity {
    private Long id;
    private String userName;
    private String userSurname;
    private String email;
}
