package com.miamato.pageobject;

import com.miamato.LogUtil;

import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v89.page.Page;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public abstract class BasePage extends Page {

    protected WebDriver driver;
    protected PageManager pageManager;

    public BasePage(WebDriver driver,PageManager pageManager){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        this.pageManager = pageManager;
    }

    @FindBy(xpath = "//input[@id='sp-cc-accept']")
    protected static WebElement ACCEPT_COOKIES_BUTTON;


    protected void enterTextIntoField(WebElement element, String text, Logger logger){
        logger.info("Entering text: \"" + text + "\" into field: " + element);
        Actions actions = new Actions(driver);
        actions.sendKeys(element, text).perform();
    }

    protected void selectFromDropdownByValue(WebElement element, String value, Logger logger){
        logger.info("Trying to select option: \"" + value + "\" from dropdown: " + element);
        Select dropdown = new Select(element);
        try {
            dropdown.selectByValue(value);
        } catch (Exception e) {
            logger.error("Option cannot be selected from dropdown");
            throw e;
        }
    }

    @Step
    protected void acceptCookiesIfPopupPresent(Logger logger){
        try{
            logger.info(" Accept cookies");
            ACCEPT_COOKIES_BUTTON.click();
        } catch(NoSuchElementException e) {
            logger.info(" Cookie accept pop-up is not displayed, so who cares");
            LogUtil.logStackTrace(e, logger);
        }
    }

}