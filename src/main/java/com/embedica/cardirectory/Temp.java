package com.embedica.cardirectory;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

// todo - если это нужно Только тебе, то есть .gitignore
public class Temp {

    public static void main(String[] args) throws ParseException {
        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(timestamp.getTime());
        System.out.println("Calendar: " + calendar.getTime());

        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString = "22-01-2015 10:20:56";
        Date date = sdf.parse(dateInString);
        calendar.setTime(date);
        System.out.println("Date  " + date);
        System.out.println("CALENDAR  " + calendar.getTime());
String rsl = "Last Date " + calendar.getTime();
        System.out.println(rsl);
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("LocalDaT " + localDateTime);
    }
}
