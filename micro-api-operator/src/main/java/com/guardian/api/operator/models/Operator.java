package com.guardian.api.operator.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Set;

@Entity
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name="OPERATORS")
@Proxy(lazy = false)
public class Operator
{
    public Operator() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @NotBlank
    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @NotBlank
    @Column(name = "contact", length = 255)
    private String contact;

    @OneToMany(fetch = FetchType.EAGER, mappedBy="operator", cascade={CascadeType.ALL}, orphanRemoval = true)
    @JsonIgnoreProperties("operator")
    private Set<Timeslot> timeslots;

    public void addTimeSlot(LocalDate start, LocalDate end) {
        timeslots.add( Timeslot.builder().operator(this).start(start).end(end).build() );
    }

    public boolean removeTimeSlot(Long id) {
        for(Timeslot time : timeslots)
            if (time.getId() == id) {
                timeslots.remove(time);
                return true;
            }
        return false;
    }
}
