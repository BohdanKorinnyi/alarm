package com.arloid.alarmcall.controller;

import com.arloid.alarmcall.service.LanguageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("languages")
@AllArgsConstructor
@Api(tags = "Operations with alarm languages")
public class LanguageController {
    private final LanguageService languageService;

    @PostMapping
    @ApiOperation(value = "Add new language for alarm")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody LanguageDto languageDto) {
        languageService.save(languageDto);
    }

    @PatchMapping
    @ApiOperation(value = "Update language introducing")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody LanguageDto languageDto) {
        languageService.update(languageDto);
    }
}
