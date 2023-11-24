package com.guardian.ui.controllers;

import com.guardian.ui.clients.*;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author luca_
 */
@Controller
@Log4j2
public class HomeController {
     /*
     @Autowired
     private AlarmRestClient alarmRestClient;
     @Autowired
     private OperatorRestClient operatorRestClient;
     */
    @Autowired
    private AppRestClient appRestClient;

    @Value("${app.title}")
    private String appTitle;

    @GetMapping(value = "/")
    public String home(Model model) {
        model.addAttribute("title", appTitle);
        val ops = appRestClient.getOperators();
        model.addAttribute("ops", ops);
        val alarms = appRestClient.getAlarms();
        model.addAttribute("alarm", alarms);
        val active = appRestClient.getActive();
        model.addAttribute("active", active);
        val alarm = new AlarmDto();
        model.addAttribute("newalarm", alarm);
        return "home";
    }

    @GetMapping(value = "/{id}")
    public String timeslots(Model model, @PathVariable Long id) {
        val op = appRestClient.getOperator(id);
        val timesl = new TimeslotDto();
        model.addAttribute("op", op);
        model.addAttribute("timesl", timesl);
        return "timeslot";
    }

    @GetMapping(value = "/info/{id}")
    public String infoOperatore(Model model, @PathVariable Long id) {
        val op = appRestClient.getOperator(id);
        model.addAttribute("op", op);
        return "infoOp";
    }

    @PostMapping(value = "/{id}")
    public String timeslot(Model model, @PathVariable Long id, @ModelAttribute TimeslotDto td) {
        appRestClient.postTimeslot(id, td);
        log.info("Post timeslot");
        return "redirect:/";
    }

    @PostMapping(value = "/")
    public String alarm(Model model, @ModelAttribute AlarmDto ad) {
        appRestClient.postAlarm(ad);
        log.info("Post allarme");
        return "redirect:/";
    }

    @PostMapping(value = "/{id}/timeslots/{timeid}")
    public String deleteTimeslot(Model model, @PathVariable Long id, @PathVariable Long timeid) {
        appRestClient.deleteTimeslot(id, timeid);
        log.info("Delete timeslot");
        return "redirect:/";
    }

    @PostMapping(value = "/alarms/{id}")
    public String deleteAlarm(Model model, @PathVariable Long id) {
        appRestClient.deleteAlarm(id);
        log.info("Delete allarme di id: " + id);
        return "redirect:/";
    }
}
