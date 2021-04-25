package com.miamato.pageobject;

import com.miamato.pageobject.amazon.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class PageManager {

    private static final Logger logger = LogManager.getLogger(PageManager.class.getSimpleName());

    public WebDriver driver;
    private HomePage homePage;
    private SearchResultsPage searchResultsPage;
    private ProductDetailsPage productDetailsPage;
    private BasketPage basketPage;
    private SignInPage signInPage;


    public PageManager(WebDriver driver){
        this.driver = driver;
    }

    public HomePage homePage(){
        if(homePage == null)
            homePage = new HomePage(driver, this);
        return homePage;
    }

    public SearchResultsPage searchResultsPage(){
        if(searchResultsPage == null)
            searchResultsPage = new SearchResultsPage(driver, this);
        return searchResultsPage;
    }

    public ProductDetailsPage productDetailsPage(){
        if(productDetailsPage == null)
            productDetailsPage = new ProductDetailsPage(driver, this);
        return productDetailsPage;
    }

    public BasketPage basketPage(){
        if(basketPage == null)
            basketPage = new BasketPage(driver, this);
        return basketPage;
    }

    public SignInPage signInPage(){
        if(signInPage == null)
            signInPage = new SignInPage(driver, this);
        return signInPage;
    }
}
