package com.arloid.alarmcall.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SaveExistingEntityException extends RuntimeException {
    public SaveExistingEntityException(String message) {
        super(message);
    }
}
