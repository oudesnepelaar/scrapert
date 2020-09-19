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
    private TimeStamper tstamper;

    @Autowired
    private WeatherService wservice;

    @Autowired
    private FeedReader nosService;
    @Autowired
    private FeedReader bbcWorldService;
    @Autowired
    private FeedReader bbcTopService;
    @Autowired
    private FeedReader bbcTechService;
    @Autowired
    private FeedReader bbcArtsService;
    @Autowired
    private FeedReader wsjService;
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

    @RequestMapping("/bbcWorld")
    public String getBBCWorldMessage() { return bbcWorldService.getCurrent(); }

    @RequestMapping("/bbcArts")
    public String getBBCArtsMessage() { return bbcArtsService.getCurrent(); }

    @RequestMapping("/bbcTech")
    public String getBBCTechMessage() { return bbcTechService.getCurrent(); }

    @RequestMapping("/bbcTop")
    public String getBBCTopMessage() { return bbcTopService.getCurrent(); }

    @RequestMapping("/wsj")
    public String getWSJMessage() { return wsjService.getCurrent(); }

    @RequestMapping("/nunl")
    public String getNUNLMessage() { return nunlService.getCurrent(); }

    @RequestMapping("/nieuwsnl")
    public String getNIEUWSNLMessage() { return nieuwsnlService.getCurrent(); }

    @RequestMapping("/mix")
    public String getMixMessage() {

        String result = "Amsterdam, " + tstamper.getFull() + " [ ";
        result += wservice.fetch() + " ] ** ";
        result += nosService.top(8);
        result += bbcWorldService.top(8);
        result += bbcArtsService.top(5);
        result += bbcTopService.top(5);
        result += bbcTechService.top(5);
        result += wsjService.top(5);
        result += nunlService.top(5);
        result += nieuwsnlService.top(5);

        return result;
    }
}
