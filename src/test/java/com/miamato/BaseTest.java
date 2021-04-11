package com.miamato;

import com.miamato.listeners.TestReporter;
import com.miamato.listeners.TestResultsListener;
import com.miamato.pageobject.amazon.BasketPage;
import com.miamato.pageobject.amazon.HomePage;
import com.miamato.pageobject.amazon.ProductDetailsPage;
import com.miamato.pageobject.amazon.SearchResultsPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

@Listeners({TestResultsListener.class, TestReporter.class})
public abstract class BaseTest {

    public static WebDriver driver;
    public static final Logger assertLogger = LogManager.getLogger("Assert");
    public static SoftAssert softAssert;

    protected HomePage homePage = null;
    protected SearchResultsPage searchResultsPage = null;
    protected ProductDetailsPage productDetailsPage = null;
    protected BasketPage basketPage = null;
    protected DriverManager driverManager = null;

    @Parameters("browserName")
    @BeforeClass
    public void setup(@Optional("Chrome") String browserName, ITestContext context){
        driverManager = new DriverManager();
        driver = driverManager.getDriver(browserName);
        context.setAttribute("WebDriver", driver);
        driver.manage().window().maximize();
        softAssert = new SoftAssert();

        homePage = new HomePage(driver);
        searchResultsPage = new SearchResultsPage(driver);
        productDetailsPage = new ProductDetailsPage(driver);
        basketPage = new BasketPage(driver);
    }

    @AfterClass
    public void cleanUp(){
        driver.quit();
    }
}
