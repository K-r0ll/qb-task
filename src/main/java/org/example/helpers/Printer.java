package org.example.helpers;

import org.example.models.CurrencyInfo;

import java.math.BigDecimal;
import java.util.Date;

public class Printer {
    private static Printer printer = null;
    private final DateHelper dateHelper = DateHelper.getInstance();

    private Printer() {

    }

    public static Printer getInstance() {
        if (printer == null) {
            printer = new Printer();
        }
        return printer;
    }

    public void waitingForData() {
        System.out.println("Aby porównać dane potrzebne są minimum dwie wartości, proszę poczekać minutę na pobranie danych");
    }

    public void printBiggestDifference(CurrencyInfo currencyInfoMIN, CurrencyInfo currencyInfoMAX, boolean shortDate) {
        BigDecimal biggestDifferenceRate = currencyInfoMAX.getCloseRate().subtract(currencyInfoMIN.getCloseRate());
        StringBuilder dateInterval = getInterval(currencyInfoMAX.getData(), currencyInfoMIN.getData(), shortDate);
        System.out.println("Największy wzrost kursu wynosi: " + biggestDifferenceRate);
        System.out.println("Przedział czasowy, w którym ten wzrost nastąpił: " + dateInterval);


    }

    private StringBuilder getInterval(Date dateOne, Date dateSecond, boolean shortDate) {
        if (dateOne.compareTo(dateSecond) > 0) {
            return buildDate(dateOne, dateSecond, shortDate);
        } else {
            return buildDate(dateSecond, dateOne, shortDate);
        }
    }

    private StringBuilder buildDate(Date dateAfter, Date dateBefore, boolean shortDate) {
        if (shortDate) {
            return new StringBuilder().append(dateHelper.getFormattedDate(dateBefore)).append(" - ").append(dateHelper.getFormattedDate(dateAfter));
        } else {
            return new StringBuilder().append(dateBefore).append(" - ").append(dateAfter);
        }

    }
}
