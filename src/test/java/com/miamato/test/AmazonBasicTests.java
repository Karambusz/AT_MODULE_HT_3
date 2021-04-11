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
    private static final String NAVIGATOR_ITEM_1 = PropertyManager.getProperty("navigator.item.1");
    private static final String NAVIGATOR_ITEM_2 = PropertyManager.getProperty("navigator.item.2");
    private static final String NAVIGATOR_ITEM_3 = PropertyManager.getProperty("navigator.item.3");
    private static final String NAVIGATOR_ITEM_4 = PropertyManager.getProperty("navigator.item.4");
    private static final String NAVIGATOR_ITEM_5 = PropertyManager.getProperty("navigator.item.5");
    private static final String NAVIGATOR_ITEM_6 = PropertyManager.getProperty("navigator.item.6");
    private static final String NAVIGATOR_ITEM_7 = PropertyManager.getProperty("navigator.item.7");
    private static final String NAVIGATOR_ITEM_8 = PropertyManager.getProperty("navigator.item.8");
    private static final List<String> EXPECTED_NAVIGATOR_ITEMS= Arrays.asList(
            NAVIGATOR_ITEM_1,
            NAVIGATOR_ITEM_2,
            NAVIGATOR_ITEM_3,
            NAVIGATOR_ITEM_4,
            NAVIGATOR_ITEM_5,
            NAVIGATOR_ITEM_6,
            NAVIGATOR_ITEM_7,
            NAVIGATOR_ITEM_8
    );
    //--------TEST 2---------
    private static final String SEARCH_TERM_1 = PropertyManager.getProperty("term.to.search.1");
    private static final String SEARCH_TERM_2 = PropertyManager.getProperty("term.to.search.2");
    private static final String SEARCH_TERM_3 = PropertyManager.getProperty("term.to.search.3");
    private static final String SEARCH_EXPECTED_DEPARTMENT_1 = PropertyManager.getProperty("search.result.department.1");
    private static final String SEARCH_EXPECTED_DEPARTMENT_2 = PropertyManager.getProperty("search.result.department.2");
    private static final String SEARCH_EXPECTED_DEPARTMENT_3 = PropertyManager.getProperty("search.result.department.3");
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
                {{SEARCH_TERM_1, SEARCH_EXPECTED_DEPARTMENT_1}
                        ,{SEARCH_TERM_2, SEARCH_EXPECTED_DEPARTMENT_2}
                        ,{SEARCH_TERM_3, SEARCH_EXPECTED_DEPARTMENT_3}};
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
                .searchByTerm(SEARCH_TERM_1);
        searchResultsPage.chooseProductToAdd();
        productDetailsPage.selectNumberOfProducts(NUMBER_OF_PRODUCTS)
                .addProductToBasket();
        assertThatTextIsPresentInField(ProductDetailsPage.BASKET, NUMBER_OF_PRODUCTS, assertLogger);
    }

    @Test
    public void addProductToCartAndThenRemove() {
        homePage.open()
                .searchByTerm(SEARCH_TERM_1);
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
