package com.checkpoint.network.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UnavilableNetworkException extends RuntimeException {

    public Integer networkId;

    public UnavilableNetworkException(String message, Integer networkId) {
        super(message);
        this.networkId = networkId;
    }
}





