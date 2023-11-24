package com.guardian.scheduler.clients;

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

    @GetMapping("/operators/active-now")
    List <OperatorDto> getActive();

}
