package com.guardian.scheduler.clients;

import lombok.Data;

@Data
public class AlarmDto {
    private long id;
    private String subject;
    private String body;
}
