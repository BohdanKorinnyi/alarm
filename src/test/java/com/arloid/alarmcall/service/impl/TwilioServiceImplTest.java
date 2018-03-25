package com.arloid.alarmcall.service.impl;

import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Say;
import org.junit.Test;

public class TwilioServiceImplTest {

    @Test
    public void test() {
        System.out.println(new VoiceResponse.Builder()
                .say(new Say.Builder("еуые")
                        .language(Say.Language.RU_RU)
                        .voice(Say.Voice.MAN)
                        .build())
                .build()
                .toXml());
    }
}