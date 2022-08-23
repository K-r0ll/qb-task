package org.example.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {

    public Date convertStringDate(String stringDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(stringDate);
    }

    public Date convertStringTime(String stringTime) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        return formatter.parse(stringTime);
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
