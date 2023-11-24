package com.guardian.ui;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author luca_
 */
@Log4j2
@EnableFeignClients()
@EnableDiscoveryClient()
@SpringBootApplication()
public class MicroUiApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(MicroUiApplication.class, args);
		log.warn("It runs. Micro UI");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if(!registry.hasMappingForPattern("/static/**")) {
			registry.addResourceHandler("/static/**")
					.addResourceLocations("classpath:/static/", "classpath:/static/js/");
		}
		if(!registry.hasMappingForPattern("/templates/**")) {
			registry.addResourceHandler("/templates/**")
					.addResourceLocations("classpath:/templates/", "classpath:/templates/");
		}
	}

}
