package com.qa.openCart.pages;

import com.qa.openCart.constants.AppConstants;
import com.qa.openCart.utils.ElementUtil;
import com.qa.openCart.utils.TimeUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    private WebDriver driver;
    private ElementUtil elementUtil;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.elementUtil = new ElementUtil(driver);
    }

    private By firstName = By.id("input-firstname");
    private By lastName = By.id("input-lastname");
    private By email = By.id("input-email");
    private By telephone = By.id("input-telephone");
    private By password = By.id("input-password");
    private By confirmpassword = By.id("input-confirm");

    private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
    private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");

    private By agreeCheckBox = By.name("agree");
    private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");

    private By successMessg = By.cssSelector("div#content h1");
    private By logoutLink = By.linkText("Logout");
    private By registerLink = By.linkText("Register");

    public boolean doRegister(String fName, String lName, String mail, String phone, String pwd, String subs) {
        elementUtil.doSendKeys(firstName, fName, TimeUtil.DEFAULT_TIME);
        elementUtil.doSendKeys(lastName, lName);
        elementUtil.doSendKeys(email, mail);
        elementUtil.doSendKeys(telephone, phone);
        elementUtil.doSendKeys(password, pwd);
        elementUtil.doSendKeys(confirmpassword, pwd);
        if (subs.equalsIgnoreCase("yes")) {
            elementUtil.doClick(subscribeYes);
        } else {
            elementUtil.doClick(subscribeNo);
        }
        elementUtil.doClick(agreeCheckBox);
        elementUtil.doClick(continueButton);
        String msg = elementUtil.waitForElementVisible(successMessg, TimeUtil.DEFAULT_TIME).getText();
        if (msg.equalsIgnoreCase(AppConstants.REGISTER_SUCCESS_MSG)) {
            elementUtil.doClick(logoutLink);
            elementUtil.doClick(registerLink);
            return true;
        } else {
            return false;
        }
    }
}
