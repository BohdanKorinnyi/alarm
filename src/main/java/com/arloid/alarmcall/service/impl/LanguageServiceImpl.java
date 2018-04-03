package com.arloid.alarmcall.service.impl;

import com.arloid.alarmcall.controller.LanguageDto;
import com.arloid.alarmcall.entity.Language;
import com.arloid.alarmcall.exception.BadLanguageQualifierException;
import com.arloid.alarmcall.exception.EmptyLanguageIntroException;
import com.arloid.alarmcall.exception.SaveExistingEntityException;
import com.arloid.alarmcall.exception.UnsupportedLanguageException;
import com.arloid.alarmcall.repository.LanguageRepository;
import com.arloid.alarmcall.service.LanguageService;
import com.arloid.alarmcall.service.TwilioService;
import lombok.AllArgsConstructor;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.function.Predicate;

import static java.util.Objects.nonNull;

@Service
@AllArgsConstructor
public class LanguageServiceImpl implements LanguageService {
    private final TwilioService twilioService;
    private final LanguageRepository languageRepository;

    @Override
    public Language findByCode(String code) {
        List<Language> all = languageRepository.findAll();
        return all.stream()
                .filter(equalsCode(code))
                .findAny()
                .orElseThrow(() -> new UnsupportedLanguageException(all));
    }

    @Override
    public void save(LanguageDto languageDto) {
        Language language = languageRepository.findByCode(languageDto.getCode());
        if (nonNull(language)) {
            throw new SaveExistingEntityException("Language " + language.getName() + " already exists in with code " + language.getCode());
        }
        if (!twilioService.isCorrectLanguageCode(languageDto.getCode())) {
            throw new UnsupportedLanguageException("Twilio doesn't support such language");
        }
        language = new Language();
        language.setName(languageDto.getName());
        language.setCode(languageDto.getCode());
        language.setIntro(languageDto.getIntro());
        languageRepository.save(language);
    }

    @Override
    public void update(LanguageDto languageDto) {
        if (StringUtils.isEmpty(languageDto.getIntro())) {
            throw new EmptyLanguageIntroException();
        }
        try {
            Language language = languageRepository.findByCodeOrName(languageDto.getCode(), languageDto.getName());
            if (nonNull(language)) {
                language.setIntro(languageDto.getIntro());
                languageRepository.save(language);
                return;
            }
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new BadLanguageQualifierException();
        }
        throw new IllegalStateException("There is no language with code " + languageDto.getCode() + " or name " + languageDto.getName());
    }

    private Predicate<Language> equalsCode(String code) {
        return language -> language.getCode().equals(code);
    }
}
