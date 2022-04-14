package com.embedica.car_directory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Temp {

    public static int containsElement(List<String> left, List<String> right, String str) {
        if (left.contains(str) & right.contains(str)) {
            left.remove(str);
            left.addAll(right);

            return left.indexOf(str);
        }
        return -1;

    }

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

    }
}
