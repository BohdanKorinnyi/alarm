package com.arloid.alarmcall.service;

public interface TwilioService {
    String makeCall(String number, String twiMlUrl);

    String createTwiMlByMessage(String message);
}
