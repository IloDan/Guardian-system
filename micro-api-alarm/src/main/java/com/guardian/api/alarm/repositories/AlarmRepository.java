package com.guardian.api.alarm.repositories;

import com.guardian.api.alarm.models.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
}
