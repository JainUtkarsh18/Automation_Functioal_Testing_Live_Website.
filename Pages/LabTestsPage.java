package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//a[contains(text(),'Login')]")
    WebElement loginBtn;

    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[1]/div[1]/div[2]/div/div[2]/div[3]/a/div[1]")
    WebElement labTestsMenu;

    @FindBy(xpath = "//div[contains(text(),'Lab Tests')]/ancestor::a")
    WebElement labTestsCard;

    //Click Login (using BasePage jsClick)
    public void clickLogin() {
        try {
            loginBtn.click();  
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", loginBtn);  
        }
        System.out.println("Login Clicked");
    }
    //Click Top Menu Lab Tests
    public void clickLabTestsMenu() {
        try {
            labTestsMenu.click();  
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", labTestsMenu);
        }
        System.out.println("Lab Tests Menu Clicked");
    }
    //Click Lab Tests Card (Unused but cleaned)
    public void clickLabTestsCard() {
        try {
            labTestsCard.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", labTestsCard);
        }
        System.out.println("Lab Tests Card Clicked");
    }
}