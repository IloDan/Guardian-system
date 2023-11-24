package com.guardian.api.alarm.controllers;

import com.guardian.api.alarm.inputs.AlarmInput;
import com.guardian.api.alarm.models.Alarm;
import com.guardian.api.alarm.repositories.AlarmRepository;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luca_
 */
@RestController
@RequestMapping(value = "/alarms")
@Log4j2
public class AlarmController {
    @Autowired
    private AlarmRepository alarmRepository;

    @GetMapping("")
    public ResponseEntity<List<Alarm>> getAll() {
        val response = alarmRepository.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/not-sent")
    public ResponseEntity<List<Alarm>> getAlarmNotSent() {
        val alarms = alarmRepository.findAll();
        val notSent = new ArrayList<Alarm>();
        for (Alarm a : alarms)
            if (a.getSentDate()==null)
                notSent.add(a);
        return new ResponseEntity<List<Alarm>>(notSent, HttpStatus.OK);
    }

    @PutMapping("/dispatched")
    public ResponseEntity setAsDispatched(@RequestParam Long id){
        try {
            val a = alarmRepository.findById(id).orElseThrow();
            a.setSentDate(LocalDate.now());
            alarmRepository.save(a);
            log.info("inserimento data spedizione");
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            log.error("not found");
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alarm> getAlarmById(@PathVariable(value = "id") Long id) {
        try {
            val response = alarmRepository.findById(id).orElseThrow();
            log.info("Restituito allarme di id: " + id);
            return new ResponseEntity<Alarm>(response, HttpStatus.OK);
        }catch (Exception e){
            log.error("not found");
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/")
    public ResponseEntity addAlarm(@RequestBody() AlarmInput input) {
        if(input!=null) {
            alarmRepository.save(Alarm.builder().subject(input.getSubject()).body(input.getBody()).build());
            log.info("Salvato nuovo allarme");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        log.info("input mancante");
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAlarmById(@PathVariable(value = "id") Long id) {
        alarmRepository.deleteById(id);
        log.info("Eliminato allarme di id: "+id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}