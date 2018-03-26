package com.arloid.alarmcall.service.impl;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Say;
import org.junit.Test;

public class TwilioServiceImplTest {

    @Test
    public void test() {
//        twilio.sid=ACfb28f9c33fc8c2d2379ec331ad282998
//        twilio.token=151f008b881a76f59c761adde15e95fe
        Twilio.init("ACfb28f9c33fc8c2d2379ec331ad282998", "151f008b881a76f59c761adde15e95fe");

        Call call = Call.fetcher("CAd5f66a833ee1fc5e2d9eb62baaf7e832").fetch();
        System.out.println(call);

    }
}