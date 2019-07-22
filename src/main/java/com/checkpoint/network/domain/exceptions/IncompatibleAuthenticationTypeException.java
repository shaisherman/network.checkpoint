package com.checkpoint.network.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class IncompatibleAuthenticationTypeException extends RuntimeException {
    public IncompatibleAuthenticationTypeException(String message) {
        super(message);
    }
}
