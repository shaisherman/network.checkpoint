package com.checkpoint.network.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UnavailableDeviceException extends RuntimeException {

    public String deviceId;
    public Integer networkId;

    public UnavailableDeviceException(String message, Integer networkId, String deviceId) {
        super(message);
        this.deviceId = deviceId;
        this.networkId = networkId;
    }
}


