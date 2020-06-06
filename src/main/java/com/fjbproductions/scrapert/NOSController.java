package com.fjbproductions.scrapert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NOSController {

    @Autowired
    private NOSService service;

    @RequestMapping("/test")
    public String testMessage() {
        return "This is the NOSController, bitches!";
    }

    @RequestMapping("/nos")
    public String getMessage() {

        return service.getCurrent();
    }
}
