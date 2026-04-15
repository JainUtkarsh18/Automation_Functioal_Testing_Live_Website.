package com.pages;

import org.openqa.selenium.*;

public class HealthConcernSection extends BasePage {

    HomePage home;
    LoginPage login;
    LabTestsPage labPage;

    public HealthConcernSection(WebDriver driver) {
        super(driver);
        home = new HomePage(driver);
        login = new LoginPage(driver);
        labPage = new LabTestsPage(driver);
    }
    // Login
    public void loginToApplication(String phone, String password) {
        home.clickLogin();
        login.loginWithPassword(phone, password);
        System.out.println("Logged in successfully");
    }
    // Open Lab Tests Page
    public void openLabTestsPage() {
        home.clickLabTestsMenu();
        System.out.println("Lab Tests page opened");
    }
    // Select City
    public void selectCity() {
        By pune = By.xpath("//div[contains(text(),'Pune')]");
        By selectedCityText = By.xpath("//input[contains(@value,'Pune')]");
        click(pune);   // BasePage click()
        WebElement city = waitVisible(selectedCityText);
        System.out.println("City selected + verified");
    }
    // Scroll to Health Concern Section
    public void scrollToHealthConcernSection() {
        By header = By.xpath("//h2[text()='Find Tests by Health Concern']");
        WebElement element = waitVisible(header);
        // Scroll to section
        scrollTo(header);
        System.out.println("Scrolled to Health Concern section");
    }
    // Scroll inside the concern section
    public void scrollHealthConcern() {
        By nextArrow = By.xpath ("//h2[normalize-space()='Find Tests by Health Concern'] /following::div[contains(@class,'slick-arrow slick-next')][1]");

        WebElement arrow = waitClickable(nextArrow);
        for (int i = 1; i <= 2; i++) {
            arrow.click();
            System.out.println("Scrolled right: " + i);
            try { Thread.sleep(400); } catch (Exception ignored) {}
        }
        System.out.println("Finished scrolling carousel");
    }
    // Click Concern
    public void clickConcern() {
        By bone = By.xpath("//div[normalize-space()='Bone']");
        WebElement boneEle = waitVisible(bone);
        waitClickable(boneEle).click();
        System.out.println("Bone clicked + verified");
    }
    //Scroll and Click on Related Questions
    public void clickRelatedQuestions() throws InterruptedException {
        By relatedQuestions = By.xpath("//div[@class='u-padt--std u-t-ellipses-dbl cut-line']");
        try {
            Thread.sleep(500); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement element = waitVisible(relatedQuestions);
        scrollTo(element);
        Thread.sleep(1000);
        waitClickable(element).click();
        // Switch to New Opened Tab
		String current = driver.getWindowHandle();
		    for (String handle : driver.getWindowHandles()) {
		        if (!handle.equals(current)) {
		            driver.switchTo().window(handle);
		            break;
		        }
		    }
    	}
    public boolean isLandedOnPage() {
    	By landedOnPage = By.xpath("//*[@id=\"feed-header\"]/input");
    	return isDisplayed(landedOnPage);
    }
}