package com.arloid.alarmcall.exception;

public class SaveExistingEntityException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public SaveExistingEntityException(String message) {
    super(message);
  }
}
