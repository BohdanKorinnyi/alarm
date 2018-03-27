package com.arloid.alarmcall.service.impl;

import com.arloid.alarmcall.service.TwiMlFileService;
import com.arloid.alarmcall.service.TwilioService;
import com.google.common.io.Files;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.Charset;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TwiMlFileServiceImpl implements TwiMlFileService {
    private static final String TWI_ML_EXTENSION = ".xml";
    private final TwilioService twilioService;

    @Override
    @SneakyThrows
    public File createSayTwiMl(String message) {
        String twiMl = twilioService.buildSayResponse(message);
        File file = new File(generateFilename());
        Files.write(twiMl, file, Charset.defaultCharset());
        return file;
    }

    @Override
    @SneakyThrows
    public File createPlayTwiMl(String message) {
        String twiMl = twilioService.buildPlayResponse(message);
        File file = new File(generateFilename());
        Files.write(twiMl, file, Charset.defaultCharset());
        return file;
    }

    private String generateFilename() {
        return UUID.randomUUID().toString() + TWI_ML_EXTENSION;
    }
}
