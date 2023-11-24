package com.guardian.api.operator.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name="TIME_SLOTS")
public class Timeslot {
    public Timeslot() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "start_time", nullable = false)
    private LocalDate start;

    @Column(name = "end_time", nullable = false)
    private LocalDate end;

    @ManyToOne
    @JoinColumn(name="operator_id", nullable=false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Operator operator;
}
