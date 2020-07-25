package com.fjbproductions.scrapert;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TimeStamper {

    final String simple = "HH:mm:ss.SSS";
    final String full = "EEEEE MMMMM yyyy HH:mm";

    public String getSimple() {

        SimpleDateFormat sdf = new SimpleDateFormat(simple);
        return sdf.format(new Date());
    }
    public String getFull() {

        SimpleDateFormat sdf = new SimpleDateFormat(full);
        return sdf.format(new Date());
    }
}
