package com.kosickaakademia.onlineworkplaceserver.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "User already exist.")
public class UserException extends RuntimeException {
}
