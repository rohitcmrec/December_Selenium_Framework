package com.qa.openCart.tests;

import com.qa.openCart.base.BaseTest;
import com.qa.openCart.constants.AppConstants;
import com.qa.openCart.errors.AppErrors;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @Test(priority = 1)
    public void verifyLoginPageTitle(){
        String title = loginPage.getLoginPageTitle();
        Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE, AppErrors.LOGIN_PAGE_TITLE_ERROR);
    }

    @Test(priority = 3)
    public void verifyLoginPageLogin(){
        loginPage.doLogin(properties.getProperty("username"), properties.getProperty("password"));
    }

    @Test(priority = 2)
    public void verifyLoginPageURL(){
        String url = loginPage.getLoginPageURL();
        Assert.assertTrue(url.contains(AppConstants.LOGIN_PAGE_URL),AppErrors.LOGIN_PAGE_URL_ERROR);
    }
}
