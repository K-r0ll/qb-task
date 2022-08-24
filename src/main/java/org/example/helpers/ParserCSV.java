package org.example.helpers;

import org.apache.commons.csv.CSVRecord;
import org.example.models.CurrencyInfo;

import java.math.BigDecimal;
import java.text.ParseException;

public class ParserCSV {
    private final DateHelper dateHelper = DateHelper.getInstance();
    private static ParserCSV parserCSV = null;

    private ParserCSV() {

    }

    public static ParserCSV getInstance() {
        if (parserCSV == null) {
            parserCSV = new ParserCSV();
        }
        return parserCSV;
    }


    public CurrencyInfo parseCsvToCurrencyInfo(CSVRecord csvRecord) throws ParseException {

        String symbol = csvRecord.get("Symbol");
        String date = csvRecord.get("Data");
        String time = csvRecord.get("Czas");
        String closeRate = csvRecord.get("Zamkniecie");
        return new CurrencyInfo.CurrencyInfoBuilder()
                .setSymbol(symbol)
                .setData(dateHelper.convertStringToDateWithTime(date, time))
                .setCloseRate(new BigDecimal(closeRate))
                .build();
    }

    public CurrencyInfo parseCsvToCurrencyInfoShort(CSVRecord csvRecord) throws ParseException {

        String date = csvRecord.get("Data");
        String closeRate = csvRecord.get("Zamkniecie");
        return new CurrencyInfo.CurrencyInfoBuilder()
                .setData(dateHelper.convertStringToDate(date))
                .setCloseRate(new BigDecimal(closeRate))
                .build();
    }
}
