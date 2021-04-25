package com.miamato.stepdefinitions;

import com.miamato.PropertyManager;
import com.miamato.context.CucumberStepContext;
import com.miamato.pageobject.PageManager;
import com.miamato.pageobject.amazon.BasketPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class RemoveProductsFromBastetSteps {
    private static final Logger logger = LogManager.getLogger(RemoveProductsFromBastetSteps.class.getSimpleName());

    public WebDriver driver = CucumberStepContext.getInstance().getDriver();
    public PageManager pageManager = CucumberStepContext.getInstance().getPageManager();

    @And("open the basket Page")
    public void openBasketPage(){
        pageManager.productDetailsPage().openBasketPage();
    }


    @And("remove all products from basket")
    public void removeAllProductsFromBasket() {
        pageManager.basketPage().removeAllFromBasket();

    }

    @Then("check that {string} lefts")
    public void checkThatNumber_of_products_after_removalLefts(String numberOfProducts) {
        logger.info("Checking if expected text: \"" + PropertyManager.getProperty(numberOfProducts)
                + "\" is equal to text in element:  " + BasketPage.BASKET);
        Assert.assertEquals(BasketPage.BASKET.getText(), PropertyManager.getProperty(numberOfProducts));
        logger.info("Text is equal to expected");
    }
}
