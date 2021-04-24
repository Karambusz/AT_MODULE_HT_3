package com.miamato.pageobject.amazon;

import com.miamato.pageobject.BasePage;
import com.miamato.pageobject.PageManager;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage extends BasePage {
    private static final Logger logger = LogManager.getLogger(ProductDetailsPage.class.getSimpleName());

    @FindBy(xpath = "//select[@id='quantity']")
    public static WebElement SELECT_NUMBER_OF_PRODUCTS;

    @FindBy(xpath = "//input[@id='add-to-cart-button']")
    public static WebElement ADD_TO_BASKET_BUTTON;

    @FindBy(xpath = "//span[@id='nav-cart-count']")
    public static WebElement BASKET;

    @FindBy(xpath = "//span[@id='nav-cart-count']//parent::div//parent::a")
    public static WebElement BASKET_LINK;

    public ProductDetailsPage(WebDriver driver, PageManager pageManager){
        super(driver, pageManager);
    }

    @Step("Selecting number of products as: {number}")
    public PageManager selectNumberOfProducts(String number){
        logger.info("Selecting number of products");
        selectFromDropdownByValue(SELECT_NUMBER_OF_PRODUCTS, number, logger);
        return this.pageManager;
    }

    @Step("Add product to basket")
    public PageManager addProductToBasket() {
        logger.info("Clicking on element " + ADD_TO_BASKET_BUTTON);
        ADD_TO_BASKET_BUTTON.click();
        return this.pageManager;
    }

    @Step("Open basket page")
    public PageManager openBasketPage() {
        logger.info("Clicking on element " + BASKET_LINK);
        BASKET_LINK.click();
        return this.pageManager;
    }




}
