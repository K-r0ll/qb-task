package org.example.services.impl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.helpers.Config;
import org.example.helpers.ParserCSV;
import org.example.helpers.Printer;
import org.example.models.CurrencyInfo;
import org.example.services.ICurrencyService;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

public class CurrencyService implements ICurrencyService {
    private final Config config = Config.getInstance();
    private final Printer printer = Printer.getInstance();
    private final ParserCSV parserCSV = ParserCSV.getInstance();
    List<CurrencyInfo> currencyInfoList = new ArrayList<>();


    private static class LoadService {
        static final CurrencyService INSTANCE = new CurrencyService();
    }

    public static CurrencyService getInstance() {
        return LoadService.INSTANCE;
    }

    private CurrencyService() {
    }


    @Override
    public void checkHistoricalCurrency() {
        List<CurrencyInfo> list = getHistoricalCurrencyFromNetwork();
        getBiggestHistoricalRise(list);
    }


    @Override
    public void checkNewCurrencyRate() {
        currencyInfoList.add(getCurrencyFromNetwork());
        getBiggestRise(currencyInfoList);
    }

    @Override
    public CurrencyInfo getCurrencyFromNetwork() {
        System.out.println(new Timestamp(new Date().getTime()));
        URL url = config.getUrlToCurrency();

        CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase();

        return getCurrencyInfoFromCsv(url, csvFormat);
    }

    @Override
    public List<CurrencyInfo> getHistoricalCurrencyFromNetwork() {
        URL url = config.getUrlToHistoricalCurrency();

        CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase();
        return getHistoricalCurrencyInfoFromCsv(url, csvFormat);
    }

    private List<CurrencyInfo> getHistoricalCurrencyInfoFromCsv(URL url, CSVFormat csvFormat) {

        LinkedList<CurrencyInfo> list = new LinkedList<>();
        if (url != null) {
            try (CSVParser csvParser = CSVParser.parse(url, StandardCharsets.UTF_8, csvFormat)) {

                for (CSVRecord csvRecord : csvParser) {

                    list.add(parserCSV.parseCsvToCurrencyInfoShort(csvRecord));

                }
            } catch (IOException | ParseException e) {
                e.printStackTrace();
                System.out.println("DZIAŁANIE PROGRAMU ZOSTAŁO PRZERWANE Z POWODU PROBLEMU Z POBRANIEM DANYCH");
                System.exit(0);
            }
        }
        return list;
    }

    private CurrencyInfo getCurrencyInfoFromCsv(URL url, CSVFormat csvFormat) {
        CurrencyInfo currencyInfo = null;
        if (url != null) {
            try (CSVParser csvParser = CSVParser.parse(url, StandardCharsets.UTF_8, csvFormat)) {

                for (CSVRecord csvRecord : csvParser) {

                    currencyInfo = parserCSV.parseCsvToCurrencyInfo(csvRecord);

                }
            } catch (IOException | ParseException e) {
                e.printStackTrace();
                System.out.println("DZIAŁANIE PROGRAMU ZOSTAŁO PRZERWANE Z POWODU PROBLEMU Z POBRANIEM DANYCH");
                System.exit(0);
            }
        }
        return currencyInfo;
    }

    private void getBiggestRise(List<CurrencyInfo> currencyInfoList) {
        if (currencyInfoList.size() == 1) {
            printer.waitingForData();
        } else {

            Optional<CurrencyInfo> currencyInfoMax = currencyInfoList.stream().max(Comparator.comparing(CurrencyInfo::getCloseRate));
            Optional<CurrencyInfo> currencyInfoMin = currencyInfoList.stream().min(Comparator.comparing(CurrencyInfo::getCloseRate));

            printer.printBiggestDifference(currencyInfoMin.get(), currencyInfoMax.get(), false);
        }
    }

    private void getBiggestHistoricalRise(List<CurrencyInfo> currencyInfoList) {

        Optional<CurrencyInfo> currencyInfoMax = currencyInfoList.stream().max(Comparator.comparing(CurrencyInfo::getCloseRate));

        Iterator<CurrencyInfo> iterator = currencyInfoList.iterator();

        while (iterator.hasNext()) {
            printer.printBiggestDifference(iterator.next(), currencyInfoMax.get(), true);
        }


    }


}
