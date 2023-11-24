package com.guardian.api.operator.inputs;

import lombok.Builder;
import lombok.Data;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;

@Data
@Builder
public class TimeslotInput {
    private LocalDate start;
    private LocalDate end;
}
