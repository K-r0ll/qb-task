package org.example.services.impl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.helpers.DateHelper;
import org.example.models.CurrencyInfo;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Optional;

public class CurrencyService {

    public CurrencyInfo getSingleCurrencyFromCsv() {
        URL url = null;
        try {
            url = new URL("https://stooq.pl/q/l/?s=usdeur&f=sd2t2ohlc&h&e=csv");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase();
        CurrencyInfo currency = null;

        try (CSVParser csvParser = CSVParser.parse(url, StandardCharsets.UTF_8, csvFormat)) {
            for (CSVRecord csvRecord : csvParser) {

                currency = parseCsvToCurrencyInfo(csvRecord);

            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return currency;
    }

    private CurrencyInfo parseCsvToCurrencyInfo(CSVRecord csvRecord) throws ParseException {
        DateHelper dateHelper = new DateHelper();

        String symbol = csvRecord.get("Symbol");
        String data = csvRecord.get("Data");
        String time = csvRecord.get("Czas");
        String closeRate = csvRecord.get("Zamkniecie");

        return new CurrencyInfo.CurrencyInfoBuilder()
                .setSymbol(symbol)
                .setData(dateHelper.convertStringDate(data))
                .setTime(dateHelper.convertStringTime(time))
                .setCloseRate(new BigDecimal(closeRate))
                .build();
    }

}
