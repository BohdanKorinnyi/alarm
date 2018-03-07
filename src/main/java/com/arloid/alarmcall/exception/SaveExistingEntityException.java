package com.arloid.alarmcall.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class SaveExistingEntityException extends RuntimeException {
    public SaveExistingEntityException(String message) {
        super(message);
    }
}
