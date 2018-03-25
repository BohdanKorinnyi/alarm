package com.arloid.alarmcall.service.impl;

import com.arloid.alarmcall.service.FileService;
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
public class FileServiceImpl implements FileService {
    private final TwilioService twilioService;

    @Override
    @SneakyThrows
    public File write(String message) {
        String twiMl = twilioService.createTwiMlByMessage(message);
        String filename = UUID.randomUUID().toString() + ".xml";
        File file = new File(filename);
        Files.write(twiMl, file, Charset.defaultCharset());
        return file;
    }
}
