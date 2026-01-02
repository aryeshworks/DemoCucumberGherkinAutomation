package com.ibm.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReusableMethods {
    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void navigatePaintMenu(WebDriverWait wait, String xPath, String category, String subCategory) {
        WebElement navItem = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
        navItem.click();

        WebElement catItem = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h4[text()='" + category + "'] | //span[text()='" + category + "']")));
        catItem.click();

        if (subCategory != null && !subCategory.isEmpty()) {
            WebElement subCatItem = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a/span[text()='" + subCategory + "']")));
            subCatItem.click();
        }
    }

    public String captureScreenshot(WebDriver driver, String testName) {
        String folderPath = System.getProperty("user.dir") + "/src/test/assets/" + testName + "/";
        File dir = new File(folderPath);
        if (!dir.exists()) dir.mkdirs();

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destPath = folderPath + testName + "_" + timestamp() + ".png";
        File destination = new File(destPath);
        try {
            FileHandler.copy(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destPath;
    }

    public String timestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
    }

    public void safeClick(WebDriverWait wait, By locator) {
        wait.until(d -> {
            try {
                WebElement element = d.findElement(locator);
                element.click();
                return true;
            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                return false;
            }
        });
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.loading-mask")));
    }

    public int getQtyValue(WebDriverWait wait, By locator) {
        String val = wait.until(ExpectedConditions.presenceOfElementLocated(locator)).getAttribute("value");
        return Integer.parseInt(val);
    }
}
