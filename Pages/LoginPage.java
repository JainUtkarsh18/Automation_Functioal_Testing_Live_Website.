package com.pages;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LabTestsPage extends BasePage {

    WebDriverWait wait;

    public LabTestsPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    // Select City
    public void selectPuneCity() {
        By pune = By.xpath("//div[contains(text(),'Pune')]");
        By selectedCityText = By.xpath("//input[contains(@value,'Pune')]");
        click(pune);   // BasePage click()
        WebElement city = waitVisible(selectedCityText);
        System.out.println("City is selected");
    }
    // Add One Item
    public void addOneItemToCart() {
        By firstAddToCart = By.xpath ("(//div[@class='o-f-color--plight' and normalize-space()='ADD TO CART'])[1]");
        click(firstAddToCart);
        System.out.println("First Item added");
    }
    // Add Two Items
    public void addTwoItemToCart() {
        By secondAddToCart = By.xpath ("//*[@id=\"root-app\"]/div/div/div[2]/div[2]/div[2]/div/div[2]/div/div[2]/div/div[2]/div/div/div/div/div"); //("(//div[@class='o-f-color--plight' and normalize-space()='ADD TO CART'])[2]"); 
        click(secondAddToCart);
        System.out.println("Second item added");
    }
    public boolean isTwoItemsAddedToCart() {
    	By cartTitle = By.xpath("//span[text() = '2 Tests']");
    	return isDisplayed(cartTitle);
    }
    public void smallScrollDown() {
        scrollDown(250);   
    }
    // Apply Coupon
    public void applyCoupon(String couponCode) throws InterruptedException {
        By applyCouponBox = By.xpath("//img[@src='/tests/public/icons/icon_forward.svg']"); 
        scrollTo(applyCouponBox);
        jsClick(applyCouponBox);
        Thread.sleep(2000);
        By couponInput = By.xpath("//input[@placeholder='Enter Coupon Code']");
        type(couponInput, couponCode);
        By applyButton = By.xpath("//span[text()='Apply']");
        jsClick(applyButton);
        System.out.println("Coupon Applied");
    }
    // Close Coupon Pop up
    public void closeCouponPopup() {
        try {
            Thread.sleep(800);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
            System.out.println("Closed coupon popup (ESC)");
        } catch (Exception e) {
            js.executeScript("document.querySelectorAll('.ReactModal__Overlay').forEach(e => e.remove());");
        }
    }
    // Proceed to Checkout
    public void clickProceedToCheckout() {
        By proceedBtn = By.xpath ("//div[normalize-space()='Proceed to Checkout']"); //("//*[@id=\"root-app\"]/div/div/div[2]/div[1]/div/div[1]/div/div[2]/div/div/a/div");
        click(proceedBtn);
        System.out.println("Proceed to Checkout clicked");
    }
	public boolean isErrorDisplayed() {
		By ErrorMsg = By.xpath("//div[text() = 'Coupon code not found']");
		return isDisplayed(ErrorMsg);
	}
}