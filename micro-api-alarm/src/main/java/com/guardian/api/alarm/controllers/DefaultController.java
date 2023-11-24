package com.guardian.api.alarm.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luca_
 */
@RestController
@RequestMapping(value = "/")
public class DefaultController {
    @Value( "${app.title}" )
    private String appTitle;

    @RequestMapping("")
    public String getIndex(){
        return String.format("%s Version 1.0", this.appTitle);
    }
}
