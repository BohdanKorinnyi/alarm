package com.arloid.alarmcall.exception;

import com.arloid.alarmcall.entity.Language;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import static java.util.stream.Collectors.toList;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsupportedLanguageException extends RuntimeException {
    public UnsupportedLanguageException(String message) {
        super(message);
    }

    public UnsupportedLanguageException(List<Language> supported) {
        super("Supported languages: " + supported.stream().map(Language::getCode).collect(toList()));
    }
}
