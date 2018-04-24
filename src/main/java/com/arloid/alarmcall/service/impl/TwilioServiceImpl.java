package com.arloid.alarmcall.service.impl;

import com.arloid.alarmcall.configuration.TwilioProperties;
import com.arloid.alarmcall.entity.Language;
import com.arloid.alarmcall.exception.UnsupportedLanguageException;
import com.arloid.alarmcall.service.TwilioService;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Pause;
import com.twilio.twiml.voice.Play;
import com.twilio.twiml.voice.Say;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class TwilioServiceImpl implements TwilioService {
  private static final int INTRO_PAUSE_SECONDS = 2;
  private static final Pause PAUSE = new Pause.Builder().length(INTRO_PAUSE_SECONDS).build();

  private final TwilioProperties twilioProperties;
  private final PhoneNumber fromPhoneNumber;

  private BiFunction<String, String, Say> alarmMessage =
      (message, code) ->
          new Say.Builder(message).voice(Say.Voice.MAN).language(getTwilioLanguage(code)).build();

  @Override
  @SneakyThrows
  public Call makeCall(String toNumber, long clientId) {
    return Call.creator(
            new PhoneNumber(toNumber),
            fromPhoneNumber,
            UriComponentsBuilder.fromHttpUrl(twilioProperties.getAlarmEndpoint() + clientId)
                .build()
                .toUri())
        .create();
  }

  @Override
  public String buildSayResponse(String message, Language language) {
    Say intro = intro().apply(language);
    log.info("build");
    return new VoiceResponse.Builder()
        .say(intro)
        .pause(PAUSE)
        .say(intro)
        .pause(PAUSE)
        .say(alarmMessage.apply(message, language.getCode()))
        .play(
            new Play.Builder(
                    "http://ec2-54-244-191-28.us-west-2.compute.amazonaws.com:8080/alarms/test")
                .build())
        .build()
        .toXml();
  }

  @Override
  public String buildPlayResponse(String url, Language language) {
    Say intro = intro().apply(language);
    return new VoiceResponse.Builder()
        .say(intro)
        .pause(PAUSE)
        .say(intro)
        .pause(PAUSE)
        .play(new Play.Builder(url).build())
        .play(
            new Play.Builder(
                    "http://ec2-54-244-191-28.us-west-2.compute.amazonaws.com:8080/alarms/test")
                .build())
        .build()
        .toXml();
  }

  @Override
  public boolean isCorrectLanguageCode(String code) {
    return Arrays.stream(Say.Language.values())
        .map(Say.Language::toString)
        .anyMatch(v -> v.equals(code));
  }

  private Function<Language, Say> intro() {
    return language ->
        new Say.Builder(language.getIntro())
            .language(getTwilioLanguage(language.getCode()))
            .build();
  }

  private Say.Language getTwilioLanguage(String code) {
    return Arrays.stream(Say.Language.values())
        .filter(l -> l.toString().equals(code))
        .findFirst()
        .orElseThrow(
            () -> new UnsupportedLanguageException("Language " + code + " doesn't support"));
  }
}
