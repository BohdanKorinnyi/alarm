package com.arloid.alarmcall.service;

import com.twilio.rest.api.v2010.account.Call;

public interface TwilioService {
    Call makeCall(String number, String twiMlUrl);

    String buildSayResponse(String message);

    String buildPlayResponse(String message);
}