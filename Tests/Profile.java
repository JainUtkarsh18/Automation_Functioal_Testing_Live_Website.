package com.tests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.pages.HomePage;
import com.pages.LabTestsPage;
import com.pages.LoginPage;
import com.parameter.ConfigReader;
import com.parameter.DataProviderReader;
import com.setup.BaseSteps;
import com.utils.ExtentManager;
import com.pages.CheckOutPage;
import com.pages.HealthConcernSection;
import com.pages.SearchBar;
import com.pages.VitalCheckupsSection;
import com.pages.AppLink;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.beust.jcommander.Parameter;
import com.utils.ExtentManager;
import com.utils.Screenshots;

public class Profile extends BaseSteps {
    HomePage home;
    LoginPage login;
    LabTestsPage labPage;
    CheckOutPage checkOut;
    HealthConcernSection health;
    SearchBar search;
    VitalCheckupsSection vital;
    AppLink app;
    /*
	 * Created By: Utkarsh Jain
	 * Reviewed By: Aparna Savant
	 * Description: To validate login, city selection, 
	 * 				and addition of multiple lab tests to the cart.
	 */
    @Test(priority = 1, 
    		description = "Login then Navigate to Lab Tests Page then Select City and Add to Cart", 
    		enabled = true)
    public void testCase1_launchAndAddToCart() {
    	home = new HomePage(getDriver());
        login = new LoginPage(getDriver());
        labPage = new LabTestsPage(getDriver());
        checkOut = new CheckOutPage(getDriver());
        search = new SearchBar(getDriver());
        // Login
        home.clickLogin();
        login.loginWithPassword(
                ConfigReader.getProperty("phone"),
                ConfigReader.getProperty("password"));
        // Navigate to Lab Tests Page
        home.clickLabTestsMenu();
        // Select City
        labPage.selectPuneCity();
        // Added items into the cart
        labPage.addOneItemToCart();
        labPage.addTwoItemToCart();
        test.info("Added items in cart");
        test.info("Cart shows the added items");
        //Assert
        Assert.assertTrue(labPage.isTwoItemsAddedToCart(), "There are no items in cart");
        System.out.println("TEST CASE 1 PASSED");
        }
    /*
	 * Created By: Utkarsh Jain
	 * Reviewed By: Aparna Savant
	 * Description: To validate the error message displayed for an invalid coupon code.
	 * 				(Given the invalid input)
	 */
     @Test(priority = 2,
    		 dataProvider = "couponData",
    		 dataProviderClass = DataProviderReader.class,
    		 dependsOnMethods = "testCase1_launchAndAddToCart",
    		 description= "Apply Coupon",
    		 enabled= true)
     public void testCase2_couponProcess(
    		 String coupon) throws InterruptedException {
    	 //Scroll Down
    	 labPage.smallScrollDown();
    	 test.info("Applying Coupon");
         // Apply coupon
         labPage.applyCoupon(coupon);
         test.info("Invalid coupon enetered");
         System.out.println("TEST CASE 2 PASSED");
         //Assert
         Assert.assertTrue(labPage.isErrorDisplayed(), "No such error found");
         }
     /*
 	 * Created By: Utkarsh Jain
 	 * Reviewed By: Aparna Savant
 	 * Description: To validate the complete checkout flow and 
 	 * 				proceeding for Pay Now.
 	 */
    @Test(priority = 3, 
    		dataProvider = "ExcelData",
    		dataProviderClass = DataProviderReader.class,
    		dependsOnMethods = "testCase2_couponProcess", 
    		description= "Complete Checkout Flow", 
    		enabled = true)
    public void testCase3_checkoutFlow(
    		String age,
    		String email) {
        // Close Pop up
        labPage.closeCouponPopup();
        labPage.clickProceedToCheckout();
        test.info("Close the Coupon PopUp and Proceed to checkout");
        // Patient details
        checkOut.enterDOB(age);
        checkOut.selectGenderMale();
        checkOut.enterEmail(email);
        test.info("Entered the data");
        // Continue to next page
        checkOut.clickContinue();
        checkOut.clickContinue2();
        test.info("Address selected");
        checkOut.chooseTimeSlot();
        test.info("Time Slot Seleccted");
        checkOut.payNow();
        test.info("Pay Now Option Clicked");
        //Assert
        Assert.assertTrue(checkOut.isPayNowOptionDisplayed(), "Pay now button is not displayed");
        System.out.println("TEST CASE 3 PASSED");
    	} 
    /*
	 * Created By: Utkarsh Jain
	 * Reviewed By: Aparna Savant
	 * Description: To validate navigation and content access 
	 * 				from the Health Concern section in the Lab Tests page.
	 */
    @Test (priority = 4, 
    		description= "Complete Flow of Health Concern Section", 
    		enabled = true,
    		groups = "lab_extended_flow")
    public void testCase4_healthConcernSection() throws InterruptedException {
        WebDriver newDriver = new ChromeDriver();
        BaseSteps.tlDriver.set(newDriver);
        newDriver.manage().window().maximize();
        newDriver.get("https://www.practo.com/");
        HealthConcernSection health = new HealthConcernSection(newDriver);
        health.loginToApplication(
                ConfigReader.getProperty("phone"),
                ConfigReader.getProperty("password"));
        health.openLabTestsPage();
        health.selectCity();
        //Scroll to Health Concern Section
        health.scrollToHealthConcernSection();
        //Scroll into Health Concern Section
        health.scrollHealthConcern();
        //Select the concern
        health.clickConcern();
        test.info("Scroll and Click on concern");
        //Scroll on Articles and Click on Related Questions
        health.clickRelatedQuestions();
        test.info("Click on related article");
        //Assert
        Assert.assertTrue(health.isLandedOnPage(),"Not landed on Page");
        System.out.println("TEST CASE 4 PASSED");
    	}
    /*
	 * Created By: Utkarsh Jain
	 * Reviewed By: Aparna Savant
	 * Description: To validate the functionality of the Lab Tests search bar 
	 * 				and result navigation and selection of different test.
	 */
    @Test (priority = 5,
    		dataProvider = "searchData",
    		dataProviderClass = DataProviderReader.class,
    		description= "Complete Flow of Search Bar" ,
    		enabled = true,
    		groups = "lab_extended_flow")
    public void testCase5_searchBarFunctionality(
    		String searchKeyWord) {
        WebDriver newDriver = new ChromeDriver();
        BaseSteps.tlDriver.set(newDriver); //New Line
        newDriver.manage().window().maximize();
        newDriver.get("https://www.practo.com/");
	    HomePage home = new HomePage(newDriver);
	    LoginPage login = new LoginPage(newDriver);
	    LabTestsPage lab = new LabTestsPage(newDriver);
	    SearchBar search = new SearchBar(newDriver);
        home.clickLogin();
        login.loginWithPassword(
                ConfigReader.getProperty("phone"),
                ConfigReader.getProperty("password"));
        home.clickLabTestsMenu();
        lab.selectPuneCity();
        // Search Bar Action
        search.clickSearchBar();
        //Input Given in search bar
        search.enterSearchText(searchKeyWord);
        test.info("Entered the input in search bar");
        //Select form the options
        search.scrollToResultAndClick();
        test.info("Select the concern");
        //Add to cart and select the different test profile
        search.scrollToTop();
        test.info("Show the Landed Page");
        //Assert
        Assert.assertTrue(search.isScrolledOnPage(),"Not landed on Page");
        System.out.println("TEST CASE 5 PASSED");
    	}
    /*
	 * Created By: Utkarsh Jain
	 * Reviewed By: Aparna Savant
	 * Description: To validate adding multiple vital check up tests to the cart 
	 * 				and cart verification that it sustains the multiple operations.
	 */
    @Test (priority = 6, 
    		description= "In Vital Checkups add Three Tests back and fort and Check the Cart" , 
    		enabled = true,
    		groups = "lab_extended_flow")
    public void testCase6_vitalCheckUpsSection() {
    	 WebDriver newDriver = new ChromeDriver();
    	 BaseSteps.tlDriver.set(newDriver);//New Line
         newDriver.manage().window().maximize();
         newDriver.get("https://www.practo.com/");

 	    HomePage home = new HomePage(newDriver);
 	    LoginPage login = new LoginPage(newDriver);
 	    LabTestsPage lab = new LabTestsPage(newDriver);
        HealthConcernSection health = new HealthConcernSection(newDriver);
 	    SearchBar search = new SearchBar(newDriver);
 	    VitalCheckupsSection vital = new VitalCheckupsSection(newDriver);

 	    home.clickLogin();
         login.loginWithPassword(
                 ConfigReader.getProperty("phone"),
                 ConfigReader.getProperty("password"));
         home.clickLabTestsMenu();
         lab.selectPuneCity();
         // Scroll and Click to Vitals CheckUps
         vital.scrollAndClickTest();
         //Click to Add to Cart
         vital.clickAddToCartButton();       
         //Navigate Back
         vital.navigateBack();
         test.info("Added first Test and navigated back");
         //Click on Second Test
         vital.clickSecondTest();
         //Add to cart
         vital.addToCartButton();
         //Navigate Back
         vital.navigateToLabTestPage();
         test.info("Added second Test and navigate back");
         vital.clickThirdTest();
         vital.addToCart();
         test.info("Added third test and navigate back");
         vital.navigateToLabTest();
         //Scroll Up and Click on Cart
         vital.scrollUpToAndClickElement();
         test.info("Show cart");
         //Assert
         Assert.assertTrue(vital.isTestsInCart(),"Not landed on Page");
         System.out.println("TEST CASE 6 PASSED");
    	}
    /*
	 * Created By: Utkarsh Jain
	 * Reviewed By: Aparna Savant
	 * Description: To validate mobile number verification for the App Link feature.
	 * 				(Entered the Invalid and Valid Input)
	 */
    @Test (priority = 7, 
    		description= "In App Link Mobile Number Verification" , 
    		enabled = true,
    		groups = "lab_extended_flow")
    public void testCase7_appLinkVerification() {
    	 WebDriver newDriver = new ChromeDriver();
    	 BaseSteps.tlDriver.set(newDriver);
         newDriver.manage().window().maximize();
         newDriver.get("https://www.practo.com/");

 	    HomePage home = new HomePage(newDriver);
 	    LoginPage login = new LoginPage(newDriver);
 	    LabTestsPage lab = new LabTestsPage(newDriver);
        HealthConcernSection health = new HealthConcernSection(newDriver);
 	    SearchBar search = new SearchBar(newDriver);
 	    VitalCheckupsSection vital = new VitalCheckupsSection(newDriver);
 	    AppLink app = new AppLink(newDriver); 

         home.clickLogin();
         login.loginWithPassword(
                 ConfigReader.getProperty("phone"),
                 ConfigReader.getProperty("password"));
         System.out.println("Logged in successfully");
         home.clickLabTestsMenu();
         System.out.println("Lab Tests page opened");
         lab.selectPuneCity();
         //Scroll to the Download App Link
         //Enter Invalid Input
         app.InvalidEntery();
         Assert.assertTrue(app.isSendUnsuccesfully(),"Not Unsuccesful");
         test.info("Entered the invalid input");
         //Enter Valid Input
         app.ValidEntry();
         //Assert
         Assert.assertTrue(app.isSendSuccesfully(),"Not Succesfull");
         test.info("Entered the valid input");
         System.out.println("TEST CASE 7 PASSED");
    	}
}