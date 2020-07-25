package com.fjbproductions.scrapert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class WeatherService {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private TurboScraper weatherScraper;

    public String fetch() {

        String result = "";
        String[] values = weatherScraper.scrape();

        result += "temp: " + values[0] + "C ** ";
        result += "prec: " + values[1] + " ** ";
        result += "wind: " + values[2] + " ** ";
        result += "pres: " + values[3] + " ** ";
        result += "humi: " + values[4];

        return result;
    }

}
