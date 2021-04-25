package com.miamato.pageobject.amazon;

import com.miamato.PropertyManager;
import com.miamato.pageobject.BasePage;
import com.miamato.pageobject.PageManager;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
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
    @FindBy(xpath = "//a[@id='nav-link-accountList']")
    public static WebElement SIGN_IN;
    @FindBy(xpath = "//div[@id='glow-ingress-block']")
    public static WebElement COUNTRY_OF_DELIVER;
    @FindBy(xpath ="//a[@id='icp-nav-flyout']")
    public static WebElement CHANGE_CURRENCY_BUTTON;
    @FindBy(xpath = "//span[@id='nav-link-accountList-nav-line-1']")
    public static WebElement CUSTOMER_NAME;

    public HomePage(WebDriver driver, PageManager pageManager){
        super(driver, pageManager);
    }

    @Step("Open application home page")
    public PageManager open(){
        logger.info("Trying to open application");
        driver.navigate().to(HOME_PAGE_URL);
        Assert.assertEquals(PAGE_TITLE, driver.getTitle());
        acceptCookiesIfPopupPresent(logger);
        return this.pageManager;
    }

    @Step("Open sign in page")
    public PageManager openSignIn(){
        logger.info("Trying to open sign in page");
        SIGN_IN.click();
        return this.pageManager;
    }

    @Step("Search for a product with name: {productName}")
    public PageManager searchByTerm(String productName){
        logger.info("Performing search for product with title: " + productName);
        enterTextIntoField(SEARCH_FIELD, productName, logger);
        SEARCH_BUTTON.click();
        return this.pageManager;
    }
}
