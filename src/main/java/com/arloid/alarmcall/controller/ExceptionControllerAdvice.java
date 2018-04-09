package com.arloid.alarmcall.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.arloid.alarmcall.exception.SaveExistingEntityException;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(SaveExistingEntityException.class)
    public void handleSaveExistingEntityException(SaveExistingEntityException ex, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}
