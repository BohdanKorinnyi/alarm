package com.arloid.alarmcall.service.impl;

import com.arloid.alarmcall.configuration.TwilioProperties;
import com.arloid.alarmcall.service.TwilioService;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Play;
import com.twilio.twiml.voice.Say;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class TwilioServiceImpl implements TwilioService {
    private final TwilioProperties twilioProperties;

    private PhoneNumber fromNumber;

    @PostConstruct
    public void init() {
        fromNumber = new PhoneNumber(twilioProperties.getNumber());
    }

    @Override
    @SneakyThrows
    public Call makeCall(String toNumber, long clientId) {
        return Call.creator(new PhoneNumber(toNumber), fromNumber,
                UriComponentsBuilder.fromHttpUrl(twilioProperties.getAlarmEndpoint() + clientId).build().toUri())
                .create();
    }

    @Override
    public String buildSayResponse(String message) {
        return new VoiceResponse.Builder()
                .say(new Say.Builder(message)
                        .voice(Say.Voice.MAN)
                        .build())
                .build()
                .toXml();
    }

    @Override
    public String buildPlayResponse(String url) {
        return new VoiceResponse.Builder()
                .play(new Play.Builder(url).build())
                .build()
                .toXml();
    }
}
