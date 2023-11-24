package com.guardian.scheduler.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("gateway")
public interface AppRestClient {

    @GetMapping(value = "/")
    String getVersion();

    @GetMapping("/operators/active-now")
    List<OperatorDto> getActive();

    @GetMapping(value = "/alarms/not-sent")
    List<AlarmDto> getAlarmsNotSent();

    @PutMapping(value = "/alarms/dispatched")
    void setDispatched(@RequestParam Long id);
}
