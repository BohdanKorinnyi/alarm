package com.arloid.alarmcall.service;

import com.arloid.alarmcall.controller.LanguageDto;
import com.arloid.alarmcall.entity.Language;

public interface LanguageService {
    Language findByCode(String code);

    void save(LanguageDto languageDto);

    void update(LanguageDto languageDto);
}
