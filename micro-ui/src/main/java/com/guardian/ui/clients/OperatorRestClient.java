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
@FeignClient("micro-api-operator")
public interface OperatorRestClient {

    @GetMapping(value = "/")
    String getVersion();

    @GetMapping(value = "/operators")
    List<OperatorDto> getOperators();

    @GetMapping(value = "/operators/{id}")
    OperatorDto getOperator(@PathVariable Long id);

    @PostMapping(value = "/operators/{id}/timeslots")
    void postTimeslot(@PathVariable Long id, TimeslotDto td);

    @DeleteMapping(value = "/operators/{id}/timeslots/{timeslotId}")
    void deleteTimeslot(@PathVariable Long id, @PathVariable Long timeslotId);

    @GetMapping("/operators/active-now")
    List <OperatorDto> getActive();

}
