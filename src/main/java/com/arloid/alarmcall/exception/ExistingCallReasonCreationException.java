package com.arloid.alarmcall.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExistingCallReasonCreationException extends RuntimeException {
    public ExistingCallReasonCreationException(String message) {
        super(message);
    }
}
