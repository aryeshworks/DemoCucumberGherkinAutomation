package com.ibm.steps;

import com.ibm.hooks.Hooks;
import com.ibm.utils.ReusableMethods;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class WallpaperShoppingStepsdefs {
    WebDriver driver = Hooks.driver;
    WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(15));
    JavascriptExecutor js = (JavascriptExecutor) driver;
    ReusableMethods rs = new ReusableMethods();
    int originalNum, incrementedNum, finalNum;

    @Given("I am on the home page of the e-commerce site")
    public void i_am_on_the_home_page_of_the_e_commerce_site() {
        String webUrl = "https://www.farrow-ball.com/";
        driver.get(webUrl);
        String title = driver.getTitle();
        System.out.println("Website: " + title);
        rs.pause(5000);

        // Handle Popup
        WebElement popup = wait.until(ExpectedConditions.elementToBeClickable(By.id("CybotCookiebotDialogBodyButtonDecline")));
        if (popup.isDisplayed()) {
            rs.pause(2000);
            popup.click();
        }
    }

    @Given("I navigate to the {string} category")
    public void i_navigate_to_the_category(String category) {
        rs.navigatePaintMenu(wait, "//*[@id=\"ui-id-30\"]", category, "");
        rs.pause(2000);

//        wait.until(ExpectedConditions.titleContains(category));
//        System.out.println("Verified Navigation. Page title is: " + driver.getTitle());
//        rs.pause(2000);
    }

    @When("I select the product {string}")
    public void i_select_the_product(String productName) {
        rs.pause(2000);
        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(productName)));
        js.executeScript("arguments[0].scrollIntoView(true);", product);
        product.click();
        rs.pause(2000);
    }

    @When("I add the item to my cart")
    public void i_add_the_item_to_my_cart() {
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#maincontent > div.columns > div > div.product-top-info.wallpaper-page > div > div.product-top-info-content > div.product-info-main-custom > div.pdp-wizard > div > div.pdp-wizard-toolbar-buttons > button.action.primary")));
        addToCartButton.click();
        rs.pause(4000);
    }

    @Then("I should be able to view the product in my cart")
    public void i_should_be_able_to_view_the_product_in_my_cart() {
        WebElement goToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"modal-content-26\"]/div/div[3]/div/div[2]/a[1]")));
        rs.pause(2000);
        goToCartButton.click();
    }

    @When("I increase the quantity by {int}")
    public void i_increase_the_quantity_by(Integer incQty) {
        By qtyInput = By.cssSelector("div.control.qty > input");
        By plusBtn = By.cssSelector("div.control > button.plus");

        originalNum = rs.getQtyValue(wait, qtyInput);
        System.out.println("Original Qty: " + originalNum);

        for (int i = 0; i < incQty; i++) {
            rs.safeClick(wait, plusBtn);
            rs.pause(2000);
        }

        incrementedNum = rs.getQtyValue(wait, qtyInput);
        System.out.println("After Increment: " + incrementedNum);
    }

    @When("I decrease the quantity by {int}")
    public void i_decrease_the_quantity_by(Integer decQty) {
        By qtyInput = By.cssSelector("div.control.qty > input");
        By minusBtn = By.cssSelector("div.control > button.minus");

        for (int i = 0; i < decQty; i++) {
            rs.safeClick(wait, minusBtn);
            rs.pause(2000);
        }

        finalNum = rs.getQtyValue(wait, qtyInput);
        System.out.println("Final Qty: " + finalNum);
    }

    @Then("the cart quantity should be updated correctly")
    public void the_cart_quantity_should_be_updated_correctly() {
        if (finalNum < incrementedNum) {
            System.out.println("Success: Increment and Decrement logic verified.");
        }
        rs.pause(2000);
    }

    @When("I proceed to the checkout page")
    public void i_proceed_to_the_checkout_page() {
        WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#maincontent > div.columns > div > div.cart-container > div.cart-summary > div.cart-summary-wrapper > ul > li:nth-child(1) > button")));
        checkoutBtn.click();
        rs.pause(2000);
    }

    @Then("I should see the {string} page title")
    public void i_should_see_the_page_title(String string) {
        String pageTitle = driver.getTitle();
        System.out.println(pageTitle);
        if (pageTitle.contains("Checkout"))
            System.out.println("Product checkout feature works.");
        rs.pause(2000);
    }

    @When("I navigate back and remove the item from my cart")
    public void i_navigate_back_and_remove_the_item_from_my_cart() {
        System.out.println("Navigating back to cart page");
        driver.navigate().back();
        wait.until(ExpectedConditions.urlContains("/checkout/cart"));
        rs.pause(2000);

        WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr/td[4]/div[2]/a")));
        deleteBtn.click();
        rs.pause(2000);

        WebElement confirmDeleteBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr/div/div/div/div[1]/button")));
        confirmDeleteBtn.click();
        rs.pause(2000);
    }

    @When("I choose to continue shopping")
    public void i_choose_to_continue_shopping() {
        WebElement continueShoppingBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"maincontent\"]/div[2]/div/div[2]/p[2]/a")));
        continueShoppingBtn.click();
        rs.pause(2000);
    }

    @Then("I should be redirected back to the product catalog")
    public void i_should_be_redirected_back_to_the_product_catalog() {
        String expectedTitle = "Farrow & Ball | Handcrafted Paint and Wallpaper | Farrow & Ball";
        String title = driver.getTitle();
        Assert.assertEquals(title, expectedTitle);
        rs.pause(2000);
    }
}
