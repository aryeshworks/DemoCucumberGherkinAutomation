package com.ibm.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import java.time.Duration;

public class MyStepdefs {
    WebDriver driver;

    @Before
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("user is on login page")
    public void user_is_on_login_page() {
        driver.get("https://practicetestautomation.com/practice-test-login/");
    }

    @When("user gives correct username and correct password")
    public void user_gives_correct_username_and_correct_password() {
        driver.findElement(By.name("username")).sendKeys("student");
        driver.findElement(By.id("password")).sendKeys("Password123");
    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        driver.findElement(By.id("submit")).click();
    }

    @Then("user lands on dashboard page")
    public void user_lands_on_dashboard_page() {
        if (driver.findElement(By.linkText("Log out")).isDisplayed())
            Assert.assertTrue(true);
//            Assertions.assertTrue(true);
        else
            Assert.assertTrue(false);
//            Assertions.assertTrue(false);
    }
}
