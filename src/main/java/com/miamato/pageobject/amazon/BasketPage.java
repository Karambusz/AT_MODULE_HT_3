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

public class BasketPage extends BasePage {
    private static final Logger logger = LogManager.getLogger(BasketPage.class.getSimpleName());

    @FindBy(xpath = "//input[@value='Delete']")
    public static WebElement REMOVE_ALL_PRODUCTS_FROM_BASKET;

    @FindBy(xpath = "//span[@id='nav-cart-count']")
    public static WebElement BASKET;

    public BasketPage(WebDriver driver, PageManager pageManager){
        super(driver, pageManager);
    }

    @Step("Remove all products from basket")
    public PageManager removeAllFromBasket() {
        logger.info("Clicking on element " + REMOVE_ALL_PRODUCTS_FROM_BASKET);
        REMOVE_ALL_PRODUCTS_FROM_BASKET.click();
        return this.pageManager;
    }
}
