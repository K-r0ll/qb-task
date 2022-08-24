package org.example.services;

import org.example.models.CurrencyInfo;

import java.util.List;

public interface ICurrencyService {

    CurrencyInfo getCurrencyFromNetwork();

    void checkNewCurrencyRate();

    void checkHistoricalCurrency();

    List<CurrencyInfo> getHistoricalCurrencyFromNetwork();


}
