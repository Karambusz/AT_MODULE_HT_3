package com.miamato.stepdefinitions;

import com.miamato.PropertyManager;
import com.miamato.context.CucumberStepContext;
import com.miamato.pageobject.PageManager;
import com.miamato.pageobject.amazon.HomePage;
import com.miamato.pageobject.amazon.SearchResultsPage;
import com.miamato.pageobject.amazon.SignInPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


import java.util.List;
import java.util.Map;

public class SmokeSteps {
    private static final Logger logger = LogManager.getLogger(SmokeSteps.class.getSimpleName());

    public WebDriver driver = CucumberStepContext.getInstance().getDriver();
    public PageManager pageManager = CucumberStepContext.getInstance().getPageManager();



    @Given("customer is on home page")
    public void customerIsOnHomePage(){
        pageManager.homePage().open();
    }

    @Then("check that navigator item is present")
    public void checkThatNavigatorItemIsPresent(DataTable dataTable) {
        List<Map<String,String>> rows = dataTable.asMaps(String.class, String.class);
        int i = 0;
        for(Map<String, String> column : rows){
            checkItemInNavigator(PropertyManager.getProperty(column.get("navigator item name")), i);
            i++;
        }
        CucumberStepContext.getInstance().getSoftAssert().assertAll();
    }


    private void checkItemInNavigator(String productName, int position){
        logger.info("Checking product name");
        CucumberStepContext.getInstance().getSoftAssert().assertEquals(HomePage.NAVIGATOR_ITEMS.get(position).getText(), productName);


    }

    @Then("check text {string} in department field")
    public void checkInDepartmentField(String expectedText) throws InterruptedException {
        pageManager.searchResultsPage();
        logger.info("Checking if expected text: \"" + PropertyManager.getProperty(expectedText)
                + "\" is equal to text in element:  " + SearchResultsPage.SEARCH_RESULTS_DEPARTMENTS_IN_LEFT_MENU.get(SearchResultsPage.TARGET_DEPARTMENT_INDEX));

        Assert.assertEquals(SearchResultsPage.SEARCH_RESULTS_DEPARTMENTS_IN_LEFT_MENU.get(SearchResultsPage.TARGET_DEPARTMENT_INDEX).getText(), PropertyManager.getProperty(expectedText));
        logger.info("Text is equal to expected");

    }

    @And("redirect to sign in page")
    public void redirectToSignInPage() {
        pageManager.homePage().openSignIn();
    }

    @And("try to sign in with incorret email {string}")
    public void tryToSignInWithIncorretEmailTest_email(String email) {
        pageManager.signInPage().enterEmailIntoField(email);
    }

    @Then("check if warning text is appeared")
    public void checkIfWarningTextIsAppeared() {
        logger.info("Checking if expected box is appeared: \"" + SignInPage.MESSAGE_BOX);
        Assert.assertTrue(SignInPage.MESSAGE_BOX.isDisplayed());
        logger.info("Box displays");
    }

    @And("fill registration data")
    public void fillRegistrationData() {
        pageManager.signInPage();
        SignInPage.CREATE_ACCOUNT_LINK.click();
        pageManager.signInPage().fillCustomerRegistrationData();
    }


    @Then("check if verification message {string} will be shown")
    public void checkIfVerificationMessageVerification_messageWillBeShown(String message) {
        logger.info("Checking if expected text: \"" + PropertyManager.getProperty(message)
                + "\" is equal to text in element:  " + SignInPage.EMAIL_VERIFICATION_MESSAGE);

        Assert.assertEquals(SignInPage.EMAIL_VERIFICATION_MESSAGE.getText(), PropertyManager.getProperty(message));
        logger.info("Text is equal to expected");
    }


    @And("check country of delivery {string}")
    public void checkCountryOfDeliveryExpected_country(String country) {
        logger.info("Checking if expected text: \"" + PropertyManager.getProperty(country)
                + "\" is equal to text in element:  " + HomePage.COUNTRY_OF_DELIVER);

        String afterChanges = HomePage.COUNTRY_OF_DELIVER.getText().trim().replaceAll("\n", " ");
        Assert.assertEquals(afterChanges, PropertyManager.getProperty(country));
        logger.info("Text is equal to expected");
    }

    @And("check that currency button is displayed")
    public void checkThatCurrencyButtonIsDisplayed() {
        logger.info("Checking if expected element displays: \"" + HomePage.CHANGE_CURRENCY_BUTTON);
        Assert.assertTrue(HomePage.CHANGE_CURRENCY_BUTTON.isDisplayed());
        logger.info("Element displays");

    }

    @And("fill email")
    public void fillEmail() {
        pageManager.signInPage().inputEmail();
    }

    @And("fill password")
    public void fillPassword() {
        pageManager.signInPage().inputPassword();
    }

    @Then("check if customer title is correct {string}")
    public void checkIfCustomerTitleIsCorrectExpected_title(String title) {
        pageManager.homePage();
        logger.info("Checking if expected text: \"" + PropertyManager.getProperty(title)
                + "\" is equal to text in element:  " + HomePage.CUSTOMER_NAME);

        String afterChanges = HomePage.CUSTOMER_NAME.getText().trim().replaceAll("\n", " ");
        Assert.assertEquals(afterChanges, PropertyManager.getProperty(title));
        logger.info("Text is equal to expected");
    }
}
