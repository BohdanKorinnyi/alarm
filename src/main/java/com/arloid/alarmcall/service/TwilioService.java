package com.arloid.alarmcall.service;

import com.arloid.alarmcall.entity.Language;
import com.twilio.rest.api.v2010.account.Call;

public interface TwilioService {
  Call makeCall(String number, long clientId);

  String generateSayTwiMl(String message, Language language, long clientId);

  String generatePlayTwiMl(String message, Language language, long clientId);

  boolean isCorrectLanguageCode(String code);
}
