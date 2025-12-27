package com.qa.openCart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountsPage {

    private WebDriver driver;

    public AccountsPage(WebDriver driver){
        this.driver = driver;
    }

    //By locators
    By searchBox = By.name("search");
    By searchBtn = By.cssSelector(".btn.btn-default.btn-lg");


    public String getAccountsPageTitle(){
        String title = driver.getTitle();
        System.out.println("Accounts page title is "+title);
        return title;
    }

    public String getAccountsPageUrl(){
        String url = driver.getCurrentUrl();
        System.out.println("Accounts page url is "+url);
        return url;
    }

    public ProductPage searchItem(String prodName){
        driver.findElement(searchBox).sendKeys(prodName);
        driver.findElement(searchBox).click();
        return new ProductPage(driver);
    }
}
