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
    @Autowired
    private FeedReader bbcService;
    @Autowired
    private FeedReader nunlService;
    @Autowired
    private FeedReader nieuwsnlService;

    @RequestMapping("/test")
    public String testMessage() {
        return "Hij doet het!";
    }

    @RequestMapping("/nos")
    public String getNOSMessage() { return nosService.getCurrent(); }

    @RequestMapping("/cnn")
    public String getCNNMessage() { return cnnService.getCurrent(); }

    @RequestMapping("/bbc")
    public String getBBCMessage() { return bbcService.getCurrent(); }

    @RequestMapping("/nunl")
    public String getNUNLMessage() { return nunlService.getCurrent(); }

    @RequestMapping("/nieuwsnl")
    public String getNIEUWSNLMessage() { return nieuwsnlService.getCurrent(); }

    @RequestMapping("/mix")
    public String getMixMessage() {

        // add time
        // add current weather + 3 hour forecast
        // add Twitter hometimeline top?

        String result = "";
        result += nosService.top(3);
        result += cnnService.top(3);
        result += bbcService.top(3);
        result += nunlService.top(3);
        result += nieuwsnlService.top(3);

        return result;
    }
}
