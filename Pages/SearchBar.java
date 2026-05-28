package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(id = "username")
    WebElement phoneField;

    @FindBy(xpath = "//input[@type='password']")
    WebElement passwordField;

    @FindBy(xpath = "//button[contains(text(),'Login')]")
    WebElement loginBtn;
    // Login with phone + password
    public void loginWithPassword(String phone, String password) {
        // Type phone
        waitClickable(phoneField).sendKeys(phone);
        // Type password
        waitVisible(passwordField).sendKeys(password);
        // Click login
        try {
            loginBtn.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", loginBtn);
        }
        System.out.println("Login successful");
    }
}