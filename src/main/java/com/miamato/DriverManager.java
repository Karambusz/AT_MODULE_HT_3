package com.miamato;

import java.util.Locale;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {
    private static final Logger log = LogManager.getLogger(DriverManager.class.getSimpleName());

    public WebDriver getDriver(String driverType){

        WebDriver requestedDriver = null;
        switch (driverType.toUpperCase(Locale.ROOT)) {
            case "CHROME":
                log.info("Chrome driver selected");
                requestedDriver = initChrome();
        }
        return requestedDriver;
    }

    private WebDriver initChrome(){
        log.info("Setting up new ChromeDriver");
        System.setProperty("webdriver.chrome.driver", System.getProperty("driver.path") + "/chromedriver.exe");
        return new ChromeDriver();
    }
}
