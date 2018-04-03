package com.arloid.alarmcall.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmptyLanguageIntroException extends IllegalStateException {
    public EmptyLanguageIntroException() {
        super("System presentation can't be empty");
    }
}
