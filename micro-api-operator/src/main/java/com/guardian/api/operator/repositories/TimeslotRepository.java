package com.guardian.api.operator.repositories;

import com.guardian.api.operator.models.Timeslot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeslotRepository extends JpaRepository<Timeslot, Long> {
    List<Timeslot> findByOperatorId(Long operatorId);
}
