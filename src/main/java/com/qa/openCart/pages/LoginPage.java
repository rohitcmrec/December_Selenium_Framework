package com.qa.openCart.pages;

import com.qa.openCart.constants.AppConstants;
import com.qa.openCart.utils.ElementUtil;
import com.qa.openCart.utils.TimeUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage {

    private static final Logger log = LoggerFactory.getLogger(LoginPage.class);
    private WebDriver driver;
    private ElementUtil elementUtil;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(this.driver);
    }

    //By locators
    By userName = By.id("input-email");
    By password = By.id("input-password");
    By loginButton = By.xpath("//input[@value='Login']");
    By forgetPasswordLink = By.linkText("Forgotten Password");
    By registerPage = By.linkText("Register");

    //user actions
    public String getLoginPageTitle() {
        String title = elementUtil.waitForTitleToBe(AppConstants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_TIME);
        log.info("title of the page is {}", title);
        return title;
    }

    public String getLoginPageURL(){
        String url = elementUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL,TimeUtil.DEFAULT_TIME);
        log.info(" URL of the page is {}", url);
        return url;
    }

    public AccountsPage doLogin(String usrName, String pwd){
        elementUtil.doActionsSendKeys(userName,usrName);
        elementUtil.doActionsSendKeys(password,pwd);
        elementUtil.doClick(loginButton);
        return new AccountsPage(driver);
    }

    public RegisterPage navigateToRegisterPage(){
        elementUtil.doClick(registerPage);
        return new RegisterPage(driver);
    }
}
