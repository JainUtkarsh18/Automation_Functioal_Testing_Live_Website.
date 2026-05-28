package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AppLink extends BasePage {
	public AppLink(WebDriver driver) {
        super(driver);
    }
	public void InvalidEntery() {
		    // Element to scroll to (as per your screenshot)
		    By downloadText = By.xpath("//input[@placeholder='Enter phone number']");
		    try {
		        Thread.sleep(2000);   // small wait before scrolling
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		    WebElement textElement = waitVisible(downloadText);
		    // Scroll into view
		    scrollTo(textElement);
		    // Scroll
		    scrollDown(120);
		    By mobileInput = By.xpath("//input[@placeholder='Enter phone number']");
		    WebElement input = waitVisible(mobileInput);
		    // Click input box
		    waitClickable(input).click();
		    // Enter mobile number
		    input.sendKeys("875588945");
		    // Click Button
		     By sendLinkBtn = By.xpath("//div[normalize-space()='Send app link']");
		     WebElement sendBtn = waitClickable(sendLinkBtn);
		     sendBtn.click();
		     System.out.println("Invalid Input Given");
		}
	public void ValidEntry() {
	By mobileInput = By.xpath("//input[@placeholder='Enter phone number']");
	    try { 
	        Thread.sleep(500);  
	    } catch (Exception e) { 
	        e.printStackTrace(); 
	    }
	    WebElement inputBox = waitVisible(mobileInput);
	    waitClickable(inputBox).click();
	    // Removing invalid input
	    inputBox.sendKeys(Keys.CONTROL + "a");
	    inputBox.sendKeys(Keys.DELETE);
	    // Enter VALID number
	    inputBox.sendKeys("8755889457");
	    // Click BUtton
	     By sendLinkBtn = By.xpath("//div[normalize-space()='Send app link']");
	     WebElement sendBtn = waitClickable(sendLinkBtn);
	     sendBtn.click();
	    System.out.println("Valid number entered");
	}
	public boolean isSendSuccesfully() {
		By smsSuccesfull = By.xpath("//div[normalize-space()='SMS sent successfully']");
		return isDisplayed(smsSuccesfull);
	}
	public boolean isSendUnsuccesfully() {
		By smsUnSuccesfull = By.xpath("//div[contains(text(),'Enter valid mobile number')]");
		return isDisplayed(smsUnSuccesfull);
	}
}
