package org.example.models;

import java.math.BigDecimal;
import java.util.Date;

public class CurrencyInfo {
    private String symbol;
    private Date date;
    private BigDecimal closeRate;

    private CurrencyInfo(CurrencyInfoBuilder builder) {
        this.symbol = builder.symbol;
        this.date = builder.date;
        this.closeRate = builder.closeRate;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Date getData() {
        return date;
    }

    public void setData(Date data) {
        this.date = data;
    }


    public BigDecimal getCloseRate() {
        return closeRate;
    }

    public void setCloseRate(BigDecimal closeRate) {
        this.closeRate = closeRate;
    }

    @Override
    public String toString() {
        return "CurrencyInfo{" +
                "symbol='" + symbol + '\'' +
                ", data=" + date +
                ", closeRate=" + closeRate +
                '}';
    }

    public static class CurrencyInfoBuilder {
        private String symbol;
        private Date date;

        private BigDecimal closeRate;

        public CurrencyInfoBuilder() {
        }

        public CurrencyInfoBuilder setSymbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public CurrencyInfoBuilder setData(Date date) {
            this.date = date;
            return this;
        }


        public CurrencyInfoBuilder setCloseRate(BigDecimal closeRate) {
            this.closeRate = closeRate;
            return this;
        }

        public CurrencyInfo build() {
            return new CurrencyInfo(this);
        }

    }


}
