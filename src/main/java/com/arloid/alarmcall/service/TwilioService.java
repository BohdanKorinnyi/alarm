package com.arloid.alarmcall.service;

import com.arloid.alarmcall.entity.Language;
import com.twilio.rest.api.v2010.account.Call;

public interface TwilioService {
    Call makeCall(String number, long clientId);

    String buildSayResponse(String message, Language language);

    String buildPlayResponse(String message, Language language);

    boolean isCorrectLanguageCode(String code);
}
