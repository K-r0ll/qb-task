package org.example.helpers;

import org.example.services.impl.CurrencyService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduler {
    private static Scheduler scheduler = null;
    Config config = Config.getInstance();
    CurrencyService currencyService = CurrencyService.getInstance();
    ScheduledExecutorService executorService;

    private Scheduler() {

    }

    public ScheduledExecutorService getExecutorService() {
        return executorService;
    }

    public static Scheduler getInstance() {
        if (scheduler == null) {
            scheduler = new Scheduler();
        }
        return scheduler;
    }

    public void run() {
        String appType = config.getProperties().getProperty("analyticType");

        switch (appType) {
            case "1" -> runFirstApplication();
            case "2" -> runSecondApplication();
            default -> System.out.println("Błedna konfiguracja aplikacji, dopuszczalne atrybuty analyticType to -> 1 lub 2");
        }

    }

    public void runFirstApplication() {
        executorService = Executors.newScheduledThreadPool(2);
        executorService.scheduleAtFixedRate(currencyService::checkNewCurrencyRate, 0, 1, TimeUnit.MINUTES);
        executorService.schedule(executorService::shutdown, 5, TimeUnit.MINUTES);
    }

    public void runSecondApplication() {
        currencyService.checkHistoricalCurrency();
    }


}
