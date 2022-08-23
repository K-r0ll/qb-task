package org.example.models;

import java.math.BigDecimal;
import java.util.Date;

public class CurrencyInfo {
    private String symbol;
    private Date data;
    private Date time;
    private BigDecimal closeRate;

    private CurrencyInfo(CurrencyInfoBuilder builder) {
        this.symbol = builder.symbol;
        this.data = builder.data;
        this.time = builder.time;
        this.closeRate = builder.closeRate;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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
                ", data=" + data +
                ", time=" + time +
                ", closeRate=" + closeRate +
                '}';
    }

    public static class CurrencyInfoBuilder {
        private String symbol;
        private Date data;
        private Date time;
        private BigDecimal closeRate;

        public CurrencyInfoBuilder() {
        }

        public CurrencyInfoBuilder setSymbol(String symbol) {
            this.symbol = symbol;
            return this;
        }

        public CurrencyInfoBuilder setData(Date data) {
            this.data = data;
            return this;
        }

        public CurrencyInfoBuilder setTime(Date time) {
            this.time = time;
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
