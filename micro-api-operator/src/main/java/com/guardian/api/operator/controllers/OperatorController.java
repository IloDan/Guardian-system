package com.guardian.api.operator.controllers;

import com.guardian.api.operator.inputs.TimeslotInput;
import com.guardian.api.operator.models.Operator;
import com.guardian.api.operator.models.Timeslot;
import com.guardian.api.operator.repositories.OperatorRepository;
import com.guardian.api.operator.repositories.TimeslotRepository;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luca_
 */
@RestController
@RequestMapping(value = "/operators")
@Log4j2
public class OperatorController {
    @Autowired
    private OperatorRepository operatorRepository;
    @Autowired
    private TimeslotRepository timeslotRepository;

    @GetMapping("")
    public ResponseEntity<List<Operator>> getAll() {
        val response = operatorRepository.findAll();
        log.info("Lista operatori");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}/timeslots")
    public ResponseEntity<List<Timeslot>> getAllTimeslotsById(@PathVariable(value = "id") Long id) {

        if (!operatorRepository.existsById(id)) {
            log.info("Operatore di id: " + id + "non esiste");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        val response = timeslotRepository.findByOperatorId(id);
        log.info("Restituito timeslots di operatore con id: " + id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Operator> getById(@PathVariable(value = "id") Long id) {
        log.info("entrato");
        if (!operatorRepository.existsById(id)) {
            log.info("Operatore di id: " + id + "non esiste");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        val response = operatorRepository.getById(id);
        log.info("Restituito operatore di id: " + id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/active-now")
    public ResponseEntity<ArrayList<Operator>> getActiveNow() {

        val timeslots = timeslotRepository.findAll();
        val op = new ArrayList<Operator>();
        for (Timeslot t : timeslots)
            if (LocalDate.now().isAfter(t.getStart().minusDays(1)) && LocalDate.now().isBefore(t.getEnd().plusDays(1L)))
                op.add(t.getOperator());
        log.info("Restituita lista operatori attivi ");
        return new ResponseEntity<>(op, HttpStatus.OK);
    }

    @PostMapping("/{id}/timeslots")
    public ResponseEntity addTimeslot(@PathVariable(value = "id") Long id, @RequestBody() TimeslotInput input) {

        try {
            val operator = operatorRepository.findById(id).orElseThrow();
            for (Timeslot t : operator.getTimeslots()) {
                if (t.getStart().isBefore(input.getStart()) && t.getEnd().isAfter(input.getEnd()))
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            operator.addTimeSlot(input.getStart(), input.getEnd());
            timeslotRepository.save(Timeslot.builder().operator(operator).start(input.getStart()).end(input.getEnd()).build());
            log.info("Aggiunto timeslot ad operatore di id: " + id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("not found");
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}/timeslots/{timeslotId}")
    @Transactional
    public ResponseEntity deleteTimeslotById(@PathVariable(value = "id") Long id, @PathVariable(value = "timeslotId") Long timeslotId) {
        try {
            val operator = operatorRepository.findById(id).orElseThrow();
            if (operator.removeTimeSlot(timeslotId)) {
                operatorRepository.save(operator);
                timeslotRepository.deleteById(timeslotId);
                log.info("Eliminato timeslot di id: " + timeslotId);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            log.error("not found");
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
