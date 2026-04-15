package com.pages;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class SearchBar extends BasePage {

    public SearchBar(WebDriver driver) {
        super(driver);
    }
    // Search bar field
    @FindBy(xpath = "//input[@placeholder='Search for tests, packages & profiles']")
    WebElement searchInput;

    // Search icon button
    @FindBy(xpath = "//div[contains(@class,'c-search__icon')]")
    WebElement searchIcon;
    
    // Click on search bar
    public void clickSearchBar() {
        try {
            waitClickable(searchInput).click();
        } catch (Exception e) {
            jsClick(searchInput);
        }
        System.out.println("Search bar clicked");
    }
    //Enter the input
	public void enterSearchText(String text) {
	
	        waitVisible(searchInput).clear();
	        searchInput.sendKeys(text);
	
	        System.out.println("Entered into search bar: " + text);
	        // PRESS ENTER USING ROBOT
	        try {
	            Thread.sleep(5000);   
	            Robot robot = new Robot();
	            robot.keyPress(KeyEvent.VK_ENTER);
	            robot.keyRelease(KeyEvent.VK_ENTER);
	            System.out.println("ENTER key pressed");
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("Failed to press ENTER");
	        }
	    }
	//Scroll the Result and Select another Test
	public void scrollToResultAndClick() {
	    By firstPackage = By.xpath("(//div[contains(@class,'c-package-wrapper')])[1]");
	    try { Thread.sleep(3000); } catch (Exception e) {}
	    WebElement pkg = waitVisible(firstPackage);
	    scrollTo(pkg);
	    scrollDown(150);
	    waitClickable(pkg).click();
	    System.out.println("First search result package clicked");
	}
	//Another Test is Displayed
	public void scrollToTop() {
	    try {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollTo(0, 0);");
	        System.out.println("Page scrolled to the top");
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Failed to scroll to the top");
	    }
	}
	public boolean isScrolledOnPage() {
    	By scrolledOnPage = By.xpath("//h1[normalize-space()='Jaanch - Diabetic Profile Basic']");
    	return isDisplayed(scrolledOnPage);
    }
}