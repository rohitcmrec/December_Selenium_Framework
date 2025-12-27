package com.qa.openCart.base;

import com.qa.openCart.factory.DriverManager;
import com.qa.openCart.pages.AccountsPage;
import com.qa.openCart.pages.LoginPage;
import com.qa.openCart.pages.RegisterPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.util.Properties;

public class BaseTest {
    WebDriver driver;
    DriverManager driverManager;
    protected LoginPage loginPage;
    protected Properties properties;
    protected AccountsPage accountsPage;
    protected RegisterPage registerPage;

    @Parameters({"browser"})
    @BeforeTest
    public void baseSetUp(String browser) throws MalformedURLException {
        driverManager = new DriverManager();
        properties = driverManager.initProp();
        String browserName = browser;
        if (browserName != null) {
            properties.setProperty("browser", browserName);
        }
        driver = driverManager.initDriver(properties);
        loginPage = new LoginPage(driver);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
