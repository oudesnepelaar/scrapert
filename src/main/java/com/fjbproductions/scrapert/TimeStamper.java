package com.fjbproductions.scrapert;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Component
public class TimeStamper {

    final TimeZone tz = TimeZone.getTimeZone("CET");
    final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    final SimpleDateFormat fdf = new SimpleDateFormat("EEEEE dd MMMMM yyyy - HH:mm");
    final Calendar cal = Calendar.getInstance();

    @PostConstruct
    private void setup() {

        sdf.setTimeZone(tz);
        fdf.setTimeZone(tz);
    }

    public String getSimple() {

        cal.setTime(new Date());
        return sdf.format(cal.getTime());
    }
    public String getFull() {

        cal.setTime(new Date());
        return fdf.format(cal.getTime());
    }
}
