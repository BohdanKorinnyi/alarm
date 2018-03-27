package com.arloid.alarmcall.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegistrationClientDto {
    @JsonProperty("smartHouseClientId")
    private String id;
    private String firstName;
    private String lastName;
}
