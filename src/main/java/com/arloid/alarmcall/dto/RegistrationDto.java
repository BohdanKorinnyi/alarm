package com.arloid.alarmcall.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDto {
    private Client client;
    private Alarm alarm;
    private Phone phone;

    @Getter
    @Setter
    @NoArgsConstructor
    public class Client {
        private String id;
        private String firstName;
        private String lastName;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public class Alarm {
        private com.arloid.alarmcall.entity.Alarm.AlarmRecordType type;
        private String data;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public class Phone {
        private String number;
    }
}
