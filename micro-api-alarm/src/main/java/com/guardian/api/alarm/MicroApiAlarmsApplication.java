package com.guardian.api.alarm;

import com.guardian.api.alarm.models.Alarm;
import com.guardian.api.alarm.repositories.AlarmRepository;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luca_
 */
@Log4j2
@SpringBootApplication
public class MicroApiAlarmsApplication {

	public static void main(String[] args) {

		SpringApplication.run(MicroApiAlarmsApplication.class, args);
		log.warn("It runs (Alarms Api)");
	}

}

@Log4j2
@Component
class DbSeederRunner implements CommandLineRunner {

	@Autowired
	AlarmRepository alarmRepository;

	@Override
	public void run(String... args) {
		log.warn("DbSeederRunner (Alarms Api)");
		val al=alarmRepository.count() == 0 ? new ArrayList<Alarm>() : alarmRepository.findAll();
		if(al.size()==0){
			al.add(Alarm.builder().subject("Demo").body("Demo").build());
			al.add(Alarm.builder().subject("Demo2").body("Demo2").build());
			al.forEach(alarmRepository::save);
		}
	}
}


