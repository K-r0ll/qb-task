package org.example.services.impl;

import org.example.models.CurrencyInfo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyServiceTest {
    private CurrencyService currencyService = CurrencyService.getInstance();

    @Test
    void Should_Get_Record_When_Fetch() {
        CurrencyInfo currencyInfo = currencyService.getCurrencyFromNetwork();

        assertNotNull(currencyInfo);
    }

    @Test
    void Should_Get_List_Historical_When_Fetch() {
        List<CurrencyInfo> list = currencyService.getHistoricalCurrencyFromNetwork();

        assertTrue(list.size() > 0);
    }
}