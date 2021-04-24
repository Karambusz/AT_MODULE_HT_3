package com.miamato.stepdefinitions;

import com.miamato.PropertyManager;
import com.miamato.context.CucumberStepContext;
import com.miamato.pageobject.PageManager;
import com.miamato.pageobject.amazon.ProductDetailsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;



public class AddProductSteps {

    private static final Logger logger = LogManager.getLogger(AddProductSteps.class.getSimpleName());

    public WebDriver driver = CucumberStepContext.getInstance().getDriver();
    public PageManager pageManager = CucumberStepContext.getInstance().getPageManager();


    @Given("anonymous customer is on home page")
    public void anonymousCustomerIsOnHomePage(){
        pageManager.homePage().open();
    }

    @When("customer performs search for {string}")
    public void customerPerformsSearchFor(String searchTermPropertyName) {
       pageManager.homePage().searchByTerm(PropertyManager.getProperty(searchTermPropertyName));
    }


    @And("pick the product")
    public void pickTheProduct() {
       pageManager.searchResultsPage().chooseProductToAdd();
    }

    @And("add {string} product to the basket")
    public void addProductToTheBasket(String numberOfProducts) {
        pageManager.productDetailsPage().selectNumberOfProducts(PropertyManager.getProperty(numberOfProducts))
                .productDetailsPage().addProductToBasket();
    }

    @Then("check that {string} product was added")
    public void checkThatNumber_of_productsProductWasAdded(String numberOfProducts) throws InterruptedException {
        Thread.sleep(5000);

        logger.info("Checking if expected text: \"" + PropertyManager.getProperty(numberOfProducts)
                + "\" is equal to text in element:  " + ProductDetailsPage.BASKET);

        Assert.assertEquals(ProductDetailsPage.BASKET.getText(), PropertyManager.getProperty(numberOfProducts));
        logger.info("Text is equal to expected");

    }
}
