package com.sboldur.springangularairports.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidTopException  extends RuntimeException {

    public InvalidTopException(String message, Throwable cause) {
        super(message, cause);

    }

    public InvalidTopException(String errorMessage) {
        super(errorMessage);
    }
}
