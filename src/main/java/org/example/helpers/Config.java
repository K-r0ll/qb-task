package org.example.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class Config {
    private static Config config = null;
    private final Properties properties = new Properties();

    private Config() {
        loadPropertiesFile();
    }

    public static Config getInstance() {
        if (config == null) {
            config = new Config();
        }
        return config;
    }

    private void loadPropertiesFile() {

        try (InputStream iStream = new FileInputStream("src/main/resources/app.properties")) {
            properties.load(iStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Properties getProperties() {
        return properties;
    }

    public URL getUrlToCurrency() {
        URL url = null;
        try {
            url = new URL(config.getProperties().getProperty("currencyCsvUrl"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        return url;
    }

    public URL getUrlToHistoricalCurrency() {
        URL url = null;
        try {
            url = new URL(config.getProperties().getProperty("historicalCurrencyCsvUrl"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        return url;
    }


}
