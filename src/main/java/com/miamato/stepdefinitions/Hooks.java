package com.miamato.stepdefinitions;

import com.miamato.DriverManager;
import com.miamato.context.CucumberStepContext;
import com.miamato.pageobject.PageManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;



public class Hooks {
    public DriverManager driverManager;

    @Before
    public void setup(){
        driverManager = new DriverManager();
        CucumberStepContext.getInstance().setDriver(driverManager.getDriver("Chrome"));
        CucumberStepContext.getInstance().getDriver().manage().window().maximize();
        CucumberStepContext.getInstance().setPageManager( new PageManager(CucumberStepContext.getInstance().getDriver()));
    }

    @After
    public void cleanUp(){
        CucumberStepContext.getInstance().getDriver().quit();
    }

}
