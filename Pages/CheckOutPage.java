package com.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }
    // Wait visibility
    public WebElement waitVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    // Wait visibility
    public WebElement waitVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    // Wait clickable
    public WebElement waitClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    // Wait clickable
    public WebElement waitClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    // Click
    public void click(By locator) {
        waitClickable(locator).click();
    }
    // Click
    public void click(WebElement element) {
        waitClickable(element).click();
    }
    //Check if element is displayed
    public boolean isDisplayed(By locator) {
        try {
            WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return ele.isDisplayed();
        } catch (Exception e) {
            return false;  
        }
    }
 // Check if element is displayed
    public boolean isDisplayed(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    // JS Click
    public void jsClick(By locator) {
        WebElement ele = waitClickable(locator);
        js.executeScript("arguments[0].click();", ele);
    }
    // JS Click
    public void jsClick(WebElement element) {
        waitClickable(element);
        js.executeScript("arguments[0].click();", element);
    }
    // Type 
    public void type(By locator, String text) {
        WebElement ele = waitVisible(locator);
        ele.clear();
        ele.sendKeys(text);
    }
    // Type 
    public void type(WebElement element, String text) {
        waitVisible(element);
        element.clear();
        element.sendKeys(text);
    }
    // Scroll to element 
    public void scrollTo(By locator) {
        WebElement ele = waitVisible(locator);
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", ele);
    }
    // Scroll to element
    public void scrollTo(WebElement element) {
        waitVisible(element);
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }
    // Scroll small
    public void scrollDown(int px) {
        js.executeScript("window.scrollBy(0," + px + ");");
    }
    // Assert text
    public void assertText(By locator, String expected) {
        String actual = waitVisible(locator).getText().trim();
        Assert.assertEquals(actual, expected, "❌ Text does not match!");
    }
    // Assert text
    public void assertText(WebElement element, String expected) {
        waitVisible(element);
        String actual = element.getText().trim();
        Assert.assertEquals(actual, expected, "❌ Text does not match!");
    }
    // Exists
    public boolean exists(By locator) {
        return driver.findElements(locator).size() > 0;
    }
    // Exists
    public boolean exists(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}