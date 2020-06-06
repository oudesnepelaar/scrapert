package com.fjbproductions.scrapert;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NOSController {

    @RequestMapping("/test")
    public String testMessage() {
        return "This is the NOSController, bitches!";
    }
}
