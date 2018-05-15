package com.arloid.alarmcall.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadLanguageQualifierException extends IllegalStateException {
  public BadLanguageQualifierException() {
    super("You have to set unique either code or name of the language");
  }
}
