package com.fjbproductions.scrapert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedController {

    @Autowired
    private ApplicationContext context;

    @RequestMapping("/test")
    public String testMessage() {
        return "This is the NOSController, bitches!";
    }

    @RequestMapping("/nos")
    public String getMessage() {

        FeedReader service = (FeedReader) context.getBean("nosService");
        return service.getCurrent();
    }
}
