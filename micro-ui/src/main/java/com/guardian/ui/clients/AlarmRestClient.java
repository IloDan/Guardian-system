package com.guardian.ui.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author luca_
 */
@FeignClient("micro-api-alarm")
public interface AlarmRestClient {

    @GetMapping(value = "/")
    String getVersion();
    @GetMapping(value = "/alarms")
    List<AlarmDto> getAlarms();
    @GetMapping(value = "/alarms/{id}")
    AlarmDto getAlarm(@PathVariable Long id);
    @PostMapping(value = "/alarms/")
    void postAlarm(AlarmDto ad);
    @DeleteMapping(value = "/alarms/{id}")
    void deleteAlarm(@PathVariable Long id);
}
