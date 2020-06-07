package com.fjbproductions.scrapert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScrapertController {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private FeedReader nosService;
    @Autowired
    private FeedReader cnnService;

    @RequestMapping("/test")
    public String testMessage() {
        return "Hij doet het!";
    }

    @RequestMapping("/nos")
    public String getNOSMessage() { return nosService.getCurrent(); }

    @RequestMapping("/cnn")
    public String getCNNMessage() { return cnnService.getCurrent(); }
}
