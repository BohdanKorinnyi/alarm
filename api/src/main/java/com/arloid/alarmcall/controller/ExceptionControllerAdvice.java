package com.arloid.alarmcall.controller;

import com.arloid.alarmcall.exception.SaveExistingEntityException;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ExceptionControllerAdvice {
  @SneakyThrows
  @ExceptionHandler(SaveExistingEntityException.class)
  public void handleSaveExistingEntityException(
      SaveExistingEntityException exception, HttpServletResponse response) {
    response.sendError(HttpStatus.BAD_REQUEST.value());
  }
}
