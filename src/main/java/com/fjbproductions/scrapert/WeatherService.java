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

    private final UTF8Filter filter = new UTF8Filter();

    public String fetch() {

        String result = "";
        String[] values = weatherScraper.scrape();

        result += values[0] + " C / ";
        result += values[1] + " / ";
        result += values[2] + " / ";
        result += values[4];

        return filter.filter(result);
    }

}
