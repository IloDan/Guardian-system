package com.guardian.scheduler.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author luca_
 */
@FeignClient("micro-api-alarm")
public interface AlarmRestClient {

    @GetMapping(value = "/")
    String getVersion();

    @GetMapping(value = "/alarms/not-sent")
    List<AlarmDto> getAlarmsNotSent();

    @PutMapping(value = "/alarms/dispatched")
    void setDispatched(@RequestParam Long id);
}
