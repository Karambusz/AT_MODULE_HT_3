package com.miamato.test;

import com.miamato.BaseTest;
import com.miamato.PropertyManager;
import com.miamato.pageobject.amazon.BasketPage;
import com.miamato.pageobject.amazon.HomePage;
import com.miamato.pageobject.amazon.ProductDetailsPage;
import com.miamato.pageobject.amazon.SearchResultsPage;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class AmazonBasicTests extends BaseTest {
    //--------TEST 1---------
    private static final String NAVIGATOR_BEST_SELLERS = PropertyManager.getProperty("navigator.item.1");
    private static final String NAVIGATOR_PRIME_VIDEO = PropertyManager.getProperty("navigator.item.2");
    private static final String NAVIGATOR_DEALS = PropertyManager.getProperty("navigator.item.3");
    private static final String NAVIGATOR_CUSTOMER_SERVICE = PropertyManager.getProperty("navigator.item.4");
    private static final String NAVIGATOR_PRIME = PropertyManager.getProperty("navigator.item.5");
    private static final String NAVIGATOR_RELEASES = PropertyManager.getProperty("navigator.item.6");
    private static final String NAVIGATOR_BOOKS = PropertyManager.getProperty("navigator.item.7");
    private static final String NAVIGATOR_BEAUTY = PropertyManager.getProperty("navigator.item.8");
    private static final List<String> EXPECTED_NAVIGATOR_ITEMS= Arrays.asList(
            NAVIGATOR_BEST_SELLERS,
            NAVIGATOR_PRIME_VIDEO,
            NAVIGATOR_DEALS,
            NAVIGATOR_CUSTOMER_SERVICE,
            NAVIGATOR_PRIME,
            NAVIGATOR_RELEASES,
            NAVIGATOR_BOOKS,
            NAVIGATOR_BEAUTY
    );
    //--------TEST 2---------
    private static final String SEARCH_TERM_LAPTOPS = PropertyManager.getProperty("term.to.search.1");
    private static final String SEARCH_TERM_GAMES = PropertyManager.getProperty("term.to.search.2");
    private static final String SEARCH_TERM_INTEL_CORE = PropertyManager.getProperty("term.to.search.3");
    private static final String SEARCH_EXPECTED_DEPARTMENT_COMPUTERS = PropertyManager.getProperty("search.result.department.1");
    private static final String SEARCH_EXPECTED_DEPARTMENT_GAMES = PropertyManager.getProperty("search.result.department.2");
    private static final String SEARCH_EXPECTED_DEPARTMENT_COMPONENTS = PropertyManager.getProperty("search.result.department.3");
    //--------TEST 3---------
    private static final String NUMBER_OF_PRODUCTS = PropertyManager.getProperty("product.count");
    //--------TEST 4---------
    private static final String NUMBER_OF_PRODUCTS_AFTER_REMOVAL = PropertyManager.getProperty("product.count.after.remove");

    @Test
    public void checkNavigatorItemsName() {
        homePage.open();
        assertThatItemIsPresentInNavigator(EXPECTED_NAVIGATOR_ITEMS);
    }

    @DataProvider(name = "search-term-set")
    public Object[][] searchTerms() {
        return new Object[][]
                {{SEARCH_TERM_LAPTOPS, SEARCH_EXPECTED_DEPARTMENT_COMPUTERS}
                        ,{SEARCH_TERM_GAMES, SEARCH_EXPECTED_DEPARTMENT_GAMES}
                        ,{SEARCH_TERM_INTEL_CORE, SEARCH_EXPECTED_DEPARTMENT_COMPONENTS}};
    }

    @Test(dataProvider = "search-term-set")
    public void basicAmazonProductSearch(String searchTerm, String expectedDepartmentName) {
        homePage.open()
                .searchByTerm(searchTerm);
        assertThatTextIsPresentInField(SearchResultsPage.SEARCH_RESULTS_DEPARTMENTS_IN_LEFT_MENU.get(SearchResultsPage.TARGET_DEPARTMENT_INDEX), expectedDepartmentName, assertLogger);
    }

    @Test
    public void addProductToCart() {
        homePage.open()
                .searchByTerm(SEARCH_TERM_LAPTOPS);
        searchResultsPage.chooseProductToAdd();
        productDetailsPage.selectNumberOfProducts(NUMBER_OF_PRODUCTS)
                .addProductToBasket();
        assertThatTextIsPresentInField(ProductDetailsPage.BASKET, NUMBER_OF_PRODUCTS, assertLogger);
    }

    @Test
    public void addProductToCartAndThenRemove() {
        homePage.open()
                .searchByTerm(SEARCH_TERM_LAPTOPS);
        searchResultsPage.chooseProductToAdd();
        productDetailsPage.selectNumberOfProducts(NUMBER_OF_PRODUCTS)
                .addProductToBasket()
                .openBasketPage();
        basketPage.removeAllFromBasket();
        assertThatTextIsPresentInField(BasketPage.BASKET, NUMBER_OF_PRODUCTS_AFTER_REMOVAL, assertLogger);
    }





    @Step("Check that item in navigator list exists ")
    private void assertThatItemIsPresentInNavigator(List<String> list) {
        assertLogger.info("Verifying navigator items on Home Page");
        for (int i = 0; i < list.size(); i++) {
            assertItemInNavigator(HomePage.NAVIGATOR_ITEMS.get(i).getText(), list.get(i));
        }
        softAssert.assertAll();
    }

    @Step("Check that item in navigator list is {expectedNavigatorItem} ")
    private void assertItemInNavigator(String actualNavigatorItem, String expectedNavigatorItem) {
        assertLogger.info("Verifying that " + expectedNavigatorItem + " is present in navigator list and equals " + actualNavigatorItem);
        softAssert.assertEquals(actualNavigatorItem, expectedNavigatorItem);
    }

    public static void assertThatTextIsPresentInField(WebElement element, String expectedText, Logger logger) {
        logger.info("Checking if expected text: \"" + expectedText
                + "\" is equal to text in element:  " + element);
        Assert.assertEquals(element.getText(), expectedText);
        logger.info("Text is equal to expected");
    }
}
