package com.qa.openCart.tests;

import com.qa.openCart.base.BaseTest;
import com.qa.openCart.constants.AppConstants;
import com.qa.openCart.errors.AppErrors;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AccountsPageTest extends BaseTest {

    @BeforeClass
    public void accSetUp(){
        accountsPage = loginPage.doLogin(properties.getProperty("username"), properties.getProperty("password") );
    }

    @Test
    public void verifyAccountsPageTitle(){
        String title = accountsPage.getAccountsPageTitle();
        Assert.assertEquals(title, AppConstants.ACCOUNTS_PAGE_TITLE, AppErrors.ACCOUNTS_PAGE_TITLE_ERROR);
    }

    @Test
    public void verifyAccountsPageURL(){
        String URL = accountsPage.getAccountsPageUrl();
        Assert.assertTrue(URL.contains(AppConstants.ACCOUNTS_PAGE_URL),AppErrors.ACCOUNTS_PAGE_URL_ERROR);
    }
}
