package com.utu.codetest.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

}
