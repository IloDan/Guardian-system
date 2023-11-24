package com.guardian.ui.clients;

import lombok.Data;

import java.util.Set;

@Data
public class OperatorDto {
    private long id;
    private String name;
    private String contact;
    private Set<TimeslotDto> timeslots;
}
