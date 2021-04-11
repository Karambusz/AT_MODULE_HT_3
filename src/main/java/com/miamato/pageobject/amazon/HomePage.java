package com.miamato.pageobject.amazon;

import com.miamato.PropertyManager;
import com.miamato.pageobject.BasePage;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class HomePage extends BasePage {
    private static final Logger logger = LogManager.getLogger(HomePage.class.getSimpleName());
    private final String HOME_PAGE_URL = PropertyManager.getProperty("homepage.url");
    public final String PAGE_TITLE = PropertyManager.getProperty("homepage.title");

    @FindAll(@FindBy(xpath = "//div[@id='nav-xshop']//a"))
    public static List<WebElement> NAVIGATOR_ITEMS;

    @FindBy(xpath = "//input[@id='twotabsearchtextbox']")
    public static WebElement SEARCH_FIELD;

    @FindBy(xpath = "//input[@id='nav-search-submit-button']")
    public static WebElement SEARCH_BUTTON;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    @Step("Open application home page")
    public HomePage open(){
        logger.info("Trying to open application");
        driver.navigate().to(HOME_PAGE_URL);
        Assert.assertEquals(PAGE_TITLE, driver.getTitle());
        acceptCookiesIfPopupPresent(logger);
        return this;
    }

    @Step("Search for a product with name: {productName}")
    public HomePage searchByTerm(String productName){
        logger.info("Performing search for product with title: " + productName);
        enterTextIntoField(SEARCH_FIELD, productName, logger);
        SEARCH_BUTTON.click();
        return this;
    }
}
