package com.guardian.api.operator;

import com.guardian.api.operator.models.Operator;
import com.guardian.api.operator.models.Timeslot;
import com.guardian.api.operator.repositories.OperatorRepository;
import com.guardian.api.operator.repositories.TimeslotRepository;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author luca_
 */
@Log4j2
@SpringBootApplication
public class MicroApiOperatorsApplication {

    public static void main(String[] args) {

        SpringApplication.run(MicroApiOperatorsApplication.class, args);
        log.warn("It runs (Operators Api)");
    }
}

@Log4j2
@Component
class DbSeederRunner implements CommandLineRunner {
    @Autowired
    private OperatorRepository operatorRepository;
    @Autowired
    private TimeslotRepository timeslotRepository;

    @Override
    public void run(String... args) {
        log.warn("DbSeederRunner (Operators Api)");

        val ops = operatorRepository.count() == 0 ? new ArrayList<Operator>() : operatorRepository.findAll();

        if (ops.size() == 0) {
            ops.add(Operator.builder().name("Pluto").contact("Pluto@email.it").build());
            ops.add(Operator.builder().name("Paperino").contact("Paperino@email.it").build());
            ops.forEach(operatorRepository::save);
        }

        if (timeslotRepository.count() == 0) {
            for (val op : ops) {
                timeslotRepository.save(Timeslot.builder().operator(op).start(LocalDate.now()).end(LocalDate.now().plusDays(7)).build());
            }
        }
    }
}
