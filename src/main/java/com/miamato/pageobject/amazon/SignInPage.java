package com.miamato.pageobject.amazon;

import com.miamato.pageobject.BasePage;
import com.miamato.pageobject.PageManager;
import com.miamato.valueobjects.Customer;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BasePage {
    private static final Logger logger = LogManager.getLogger(SignInPage.class.getSimpleName());

    @FindBy(xpath = "//input[@id='ap_email']")
    public static WebElement EMAIL_FIELD;
    @FindBy(xpath = "//input[@id='continue']")
    public static WebElement CONFIRM_BUTTON;
    @FindBy(xpath = "//div[@id='auth-error-message-box']")
    public static WebElement MESSAGE_BOX;
    @FindBy(xpath = "//a[@id='createAccountSubmit']")
    public static WebElement CREATE_ACCOUNT_LINK;
    @FindBy(xpath = "//input[@id='ap_customer_name']")
    public static WebElement REGISTRATION_NAME_FIELD;
    @FindBy(xpath = "//input[@id='ap_email']")
    public static WebElement REGISTRATION_EMAIL_FIELD;
    @FindBy(xpath = "//input[@id='ap_password']")
    public static WebElement REGISTRATION_PASSWORD_FIELD;
    @FindBy(xpath = "//input[@id='ap_password_check']")
    public static WebElement REGISTRATION_REENTER_PASSWORD_FIELD;
    @FindBy(xpath = "//div[@class='a-row a-spacing-small']//h1")
    public static WebElement EMAIL_VERIFICATION_MESSAGE;
    @FindBy(xpath = "//input[@id='signInSubmit']")
    public static WebElement SIGN_IN_BUTTON;

    public SignInPage(WebDriver driver, PageManager pageManager){
        super(driver, pageManager);
    }

    @Step("Search for a product with name: {email}")
    public PageManager enterEmailIntoField(String email){
        logger.info("Enter email: " + email);
        enterTextIntoField(EMAIL_FIELD, email, logger);
        CONFIRM_BUTTON.click();
        return this.pageManager;
    }

    @Step("Fill user registration data")
    public PageManager fillCustomerRegistrationData() {
        Customer customer = new Customer();
        logger.info("Entering customer name");
        REGISTRATION_NAME_FIELD.sendKeys(customer.signInName);
        logger.info("Entering customer email");
        REGISTRATION_EMAIL_FIELD.sendKeys(customer.signInEmail);
        logger.info("Entering customer password");
        REGISTRATION_PASSWORD_FIELD.sendKeys(customer.signInPassword);
        logger.info("Reentering customer password");
        REGISTRATION_REENTER_PASSWORD_FIELD.sendKeys(customer.signInPassword);
        CONFIRM_BUTTON.click();
        return this.pageManager;
    }

    @Step("Input user email")
    public PageManager inputEmail() {
        Customer customer = new Customer();
        logger.info("Entering customer email");
        REGISTRATION_EMAIL_FIELD.sendKeys(customer.email);
        CONFIRM_BUTTON.click();
        return this.pageManager;
    }

    @Step("Input user password")
    public PageManager inputPassword() {
        Customer customer = new Customer();
        logger.info("Entering customer password");
        REGISTRATION_PASSWORD_FIELD.sendKeys(customer.password);
        SIGN_IN_BUTTON.click();
        return this.pageManager;
    }


}
