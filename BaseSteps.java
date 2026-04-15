package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VitalCheckupsSection extends BasePage {

    public VitalCheckupsSection(WebDriver driver) {
        super(driver);
    }
    // Scroll and Click on First Test
    public void scrollAndClickTest() {
        By bloodSugar = By.xpath("//a[normalize-space()='Blood Sugar']");
        try {
            Thread.sleep(500);   
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement bsElement = waitVisible(bloodSugar);
        scrollTo(bsElement);
        // Slight adjustment scroll
        scrollDown(150);
        // Click the element
        waitClickable(bsElement).click();
        System.out.println("Blood Sugar element clicked successfully");
    }
    //Add to cart the test
    public void clickAddToCartButton() {
    	By addToCart = By.xpath("//div[normalize-space()='Add to Cart']");
    	    try {
    	        Thread.sleep(500);   
    	    } catch (InterruptedException e) {
    	        e.printStackTrace();
    	    }
    	    WebElement button = waitVisible(addToCart);
    	    scrollTo(button);   
    	    waitClickable(button).click();
    	    System.out.println("Add to Cart button clicked successfully");
        }
    public void navigateBack() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {}
        driver.navigate().back();
        System.out.println("Navigated back to previous page");
    }
    //Click on Second Test
    public void clickSecondTest() {
        By bloodSugar = By.xpath("//a[normalize-space()='Lipid Profile']");
        try {
            Thread.sleep(500);   
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement bsElement = waitVisible(bloodSugar);
        scrollTo(bsElement);
        // Slight adjustment scroll
        scrollDown(150);
        // Click the element
        waitClickable(bsElement).click();
        System.out.println("Lipid Profile element clicked successfully");
    }
    //Add to cart the test
    public void addToCartButton() {
    	By addToCart = By.xpath("//div[normalize-space()='Add to Cart']");
    	    try {
    	        Thread.sleep(500);   
    	    } catch (InterruptedException e) {
    	        e.printStackTrace();
    	    }
    	    WebElement button = waitVisible(addToCart);
    	    scrollTo(button);   
    	    waitClickable(button).click();
    	    System.out.println("Add to Cart button clicked successfully");
        }
    public void navigateToLabTestPage() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {}
        driver.navigate().back();
        System.out.println("Navigated back to previous page");
    }  
    //Click on Third Test
    public void clickThirdTest() {
        By bloodSugar = By.xpath("//a[normalize-space()='Liver Profile']");
        try {
            Thread.sleep(500);   
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement bsElement = waitVisible(bloodSugar);
        scrollTo(bsElement);
        // Scroll
        scrollDown(150);
        // Click the element
        waitClickable(bsElement).click();
        System.out.println("Lipid Profile element clicked successfully");
    }
    //Add to cart the test
    public void addToCart() {
    	By addToCart = By.xpath("//div[normalize-space()='Add to Cart']");
    	    try {
    	        Thread.sleep(500);   
    	    } catch (InterruptedException e) {
    	        e.printStackTrace();
    	    }
    	    WebElement button = waitVisible(addToCart);
    	    scrollTo(button);   
    	    waitClickable(button).click();
    	    System.out.println("Add to Cart button clicked successfully");
        }
    public void navigateToLabTest() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {}
        driver.navigate().back();
        System.out.println("Navigated back to previous page");
    }  
    // Show Cart
    public void scrollUpToAndClickElement() {
        By targetElement = By.xpath("//div[@class='c-global-cart u-pointer']");
        try {
            Thread.sleep(500);   
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement element = waitVisible(targetElement);
        scrollTo(element);
        // Scroll UP
        scrollDown(-150);
        // Click the element
        waitClickable(element).click();
        System.out.println("Scrolled UP and clicked the element successfully");
    }
    public boolean isTestsInCart() {
    	By testsInCart = By.xpath("//h1[contains(@class,'c-searchbar__title')]");
    	return isDisplayed(testsInCart);
    }
}