package com.arloid.alarmcall.service;

import java.io.File;

public interface TwiMlFileService {
    File createSayTwiMl(String message);

    File createPlayTwiMl(String message);
}
