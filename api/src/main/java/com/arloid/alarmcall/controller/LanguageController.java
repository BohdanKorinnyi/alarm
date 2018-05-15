package com.arloid.alarmcall.controller;

import com.arloid.alarmcall.dto.LanguageDto;
import com.arloid.alarmcall.service.LanguageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("languages")
@Api(tags = "Languages")
public class LanguageController {
  private final LanguageService languageService;

  @PostMapping
  @ApiOperation(value = "Creates a new language for alarms")
  @ResponseStatus(HttpStatus.CREATED)
  public void save(@RequestBody LanguageDto languageDto) {
    languageService.save(languageDto);
  }

  @PatchMapping
  @ApiOperation(value = "Updates the introducing message for language")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void update(@RequestBody LanguageDto languageDto) {
    languageService.update(languageDto);
  }
}
