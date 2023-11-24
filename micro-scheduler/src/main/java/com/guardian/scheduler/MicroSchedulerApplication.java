package com.guardian.scheduler;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author luca_
 */
@Log4j2
@EnableFeignClients()
@EnableDiscoveryClient()
@SpringBootApplication
public class MicroSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroSchedulerApplication.class, args);
		log.warn("It' run");
	}

}
