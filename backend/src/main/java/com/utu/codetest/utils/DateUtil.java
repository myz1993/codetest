package com.utu.codetest.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Component
public class DateUtil {
    private final SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);

    public Date stringToDate(String string) {
        try{
            return formatter.parse(string);
        }catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String dateToString(Date date) {
        return formatter.format(date);
    }

    public Date calculate(Date date, Integer type, Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(type, days);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }
}
