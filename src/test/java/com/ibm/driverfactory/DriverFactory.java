package com.ibm.driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Optional;

import java.net.MalformedURLException;
import java.net.URI;

public class DriverFactory {
    public static WebDriver createDriver(@Optional("firefox") String browser) throws MalformedURLException {
        AbstractDriverOptions<?> options;
        String hubUrl = "http://localhost:4444";
        if (browser.equalsIgnoreCase("edge")) options = new EdgeOptions();
        else options = new FirefoxOptions();
        options.setPlatformName("Windows 11");
        return new RemoteWebDriver(URI.create(hubUrl).toURL(), options);
    }
}
