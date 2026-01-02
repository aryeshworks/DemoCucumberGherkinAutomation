package com.ibm.steps;

import com.ibm.hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class MyStepdefs {
    WebDriver driver = Hooks.driver;

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

    @When("user gives {string} and {string}")
    public void userGivesAnd(String un, String pw) {
        driver.findElement(By.name("username")).sendKeys(un);
        driver.findElement(By.id("password")).sendKeys(pw);
    }
}
