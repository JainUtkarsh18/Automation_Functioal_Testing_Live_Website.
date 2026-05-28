package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckOutPage extends BasePage{

	public CheckOutPage(WebDriver driver) {
		super(driver);
		}
	// Enter DOB
    public void enterDOB(String day) {
        By dobField = By.xpath("//input[@data-aid='patient-age']"); 
        type(dobField, day);
        System.out.println("DOB entered");
    }
    // Select Gender Male
    public void selectGenderMale() {
        By maleOption = By.xpath("/html/body/div[2]/div/div/div[2]/div/div/div/div[1]/div[2]/div/div/form/div[1]/div[1]/div[3]/div[2]/div[1]/label/div[1]"); //("//input[@data-aid='patient-age']");
        click(maleOption);
        System.out.println("Gender selected");
    }
    // Enter Email
    public void enterEmail(String email) {
        By emailField = By.xpath("//input[@data-aid='patient-email']");
        type(emailField, email);
        System.out.println("Email entered");
    }
    // Continue Button
    public void clickContinue() {
        By continueBtn = By.xpath("//input[@data-aid='order-continue-button']"); 
        click(continueBtn);
        System.out.println("Continue clicked");
    }
    // Continue Address
    public void clickContinue2() {
        By continueBtn = By.xpath("//button[normalize-space()='Continue']"); 
        click(continueBtn);
        System.out.println("Address seleted and Continue clicked");
    }
    //Select Time Slot
    public void chooseTimeSlot() {
    	By slot = By.xpath("//*[@id=\"root-app\"]/div/div/div[2]/div/div/div[1]/div/div[2]/div[1]/div[2]/div/div[2]/div[2]/div[2]/div[1]/div"); ////div[@data-aid='time-slot']
    	click(slot);
    	System.out.println("Time Slot choosen");
    }
    //Pay Now
    public void payNow() {
    	By payNow = By.xpath("//div[@data-aid='review-online-confirm-booking']");
    	scrollTo(payNow);
    	click(payNow);
    	System.out.println("Pay Now clicked");
    }
    public boolean isPayNowOptionDisplayed() {
    	By PayNowBtn = By.xpath("//*[@id=\"payment-options\"]/div[2]/div[2]"); //("//div[text() = 'Pay Now']");
    	WebElement check = waitVisible(PayNowBtn);
    	return isDisplayed(check);
    }
}