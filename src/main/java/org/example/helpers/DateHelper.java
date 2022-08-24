package org.example.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
    private static DateHelper date = null;


    private DateHelper() {

    }

    public static DateHelper getInstance() {
        if (date == null) {
            date = new DateHelper();
        }
        return date;
    }

    public Date convertStringToDateWithTime(String stringDate, String stringTime) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.parse(stringDate + " " + stringTime);
    }

    public Date convertStringToDate(String stringDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(stringDate);
    }

    public String getFormattedDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    public String getFormattedTime(Date time) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        return formatter.format(time);
    }
}
