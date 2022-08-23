package org.example;

import org.example.models.CurrencyInfo;
import org.example.services.impl.CurrencyService;

public class App {
    public static void main(String[] args) {
        CurrencyService currencyService = new CurrencyService();
        CurrencyInfo currency = currencyService.getSingleCurrencyFromCsv();


    }
}
