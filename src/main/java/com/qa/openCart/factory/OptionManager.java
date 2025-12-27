package com.qa.openCart.factory;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Properties;

public class OptionManager {

    private static Properties prop;

    public OptionManager(Properties prop) {
        this.prop = prop;
    }

    public ChromeOptions getChromeOptions() {
        ChromeOptions op = new ChromeOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            op.addArguments("--headless");
        }
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            op.addArguments("--incognito");
        }
        return op;
    }

    public FirefoxOptions getFirefoxOptions() {
        FirefoxOptions op = new FirefoxOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            op.addArguments("--headless");
        }
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            op.addArguments("--incognito");
        }
        return op;
    }

    public EdgeOptions getEdgeOptions() {
        EdgeOptions op = new EdgeOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            op.addArguments("--headless");
        }
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            op.addArguments("--incognito");
        }
        return op;
    }
}
