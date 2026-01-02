@ECommerce @CartOperations
Feature: Wallpaper Shopping and Cart Management
  As a customer of Farrow & Ball
  I want to navigate through different categories of wallpapers and manage my shopping cart
  So that I can successfully purchase or modify my order

  Background:
    Given I am on the home page of the e-commerce site

  Scenario Template: Navigate to the product and manage cart lifecycle
    Given I navigate to the "<wallpaperCategory>" category
    When I select the product "<productName>"
    And I add the item to my cart
    Then I should be able to view the product in my cart

    When I increase the quantity by <incrementQty>
    And I decrease the quantity by <decrementQty>
    Then the cart quantity should be updated correctly

    When I proceed to the checkout page
    Then I should see the "Checkout" page title

    When I navigate back and remove the item from my cart
    And I choose to continue shopping
    Then I should be redirected back to the product catalog

    Examples:
    | wallpaperCategory | productName | incrementQty | decrementQty |
    | Geometric Wallpapers | Enigma | 3 | 2 |
    | Striped Wallpapers | Drag | 4 | 2 |
    | Damask Wallpapers | Silvergate | 1 | 0 |