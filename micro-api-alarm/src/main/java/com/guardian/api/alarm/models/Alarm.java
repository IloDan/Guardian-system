package com.guardian.api.alarm.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name="ALARMS")
@Proxy(lazy = false)
public class Alarm {

    public Alarm() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @NotBlank
    @Column(name = "subject", length = 255, nullable = false)
    private String subject;

    @NotBlank
    @Column(name = "body", length = 1000, nullable = false)
    private String body;

    @Column(name = "sentDate")
    private LocalDate sentDate;
}
