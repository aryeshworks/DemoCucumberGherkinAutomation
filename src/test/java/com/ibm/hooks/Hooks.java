package com.ibm.hooks;

import com.ibm.driverfactory.DriverFactory;
import com.ibm.utils.ReusableMethods;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;

public class Hooks {
    public static WebDriver driver;
    ReusableMethods rs = new ReusableMethods();

    @Before
    public void setup() throws MalformedURLException {
        String browser = System.getProperty("browser", "");
        driver = DriverFactory.createDriver(browser);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenshotPath = rs.captureScreenshot(driver, scenario.getName());
            System.err.println("[FAIL] Test Failed. Screenshot saved to: " + screenshotPath);
        }

        if (driver != null) {
            driver.quit();
        }
    }
}
