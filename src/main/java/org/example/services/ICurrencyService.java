package org.example.services;

import org.apache.commons.csv.CSVRecord;
import org.example.models.CurrencyInfo;

public interface ICurrencyService {

    public CurrencyInfo getSingleCurrencyFromCsv();


}
