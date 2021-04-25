package com.miamato.pageobject.amazon;

import com.miamato.pageobject.BasePage;
import com.miamato.pageobject.PageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;


import java.util.List;

public class SearchResultsPage extends BasePage {
    private static final Logger logger = LogManager.getLogger(SearchResultsPage.class.getSimpleName());

    @FindAll(@FindBy(xpath = "//div[@id='departments']//span[@class='a-size-base a-color-base']"))
    public static List<WebElement> SEARCH_RESULTS_DEPARTMENTS_IN_LEFT_MENU;
    @FindBy(xpath = "//div[@data-index = 1]//span[contains (@class, 'a-color-base a-text-normal')]")
    public static WebElement PRODUCT_TO_ADD_TO_CART;

    public static final int TARGET_DEPARTMENT_INDEX = 0;

    public SearchResultsPage(WebDriver driver, PageManager pageManager){
        super(driver, pageManager);
    }

    public PageManager chooseProductToAdd() {
        logger.info("Clicking on elment " + PRODUCT_TO_ADD_TO_CART);
        PRODUCT_TO_ADD_TO_CART.click();
        return this.pageManager;
    }
}
