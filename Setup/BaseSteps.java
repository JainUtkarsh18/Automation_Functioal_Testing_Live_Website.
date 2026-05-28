package com.setup;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.parameter.ConfigReader;
import com.utils.ExtentManager;
import com.utils.Screenshots;

public class BaseSteps {
    // ThreadLocal Driver
    protected static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    // Getter for the CURRENT driver
    public WebDriver getDriver() {
        return tlDriver.get();
    }
   public ExtentReports extent;
    public ExtentTest test;
    // Setup for FIRST driver
    @BeforeClass
    public void setup() {

        WebDriver driver = new ChromeDriver();
        tlDriver.set(driver);   // Store in ThreadLocal

        getDriver().manage().window().maximize();
        getDriver().get(ConfigReader.getProperty("url"));

        System.out.println("Browser launched and URL opened");
    }
    @BeforeSuite
    public void setupReport() {
        extent = ExtentManager.getInstance();
    }
    @BeforeMethod
    public void BeforeMethod(Method method) {
        test = extent.createTest("Test: " + method.getName());
    }
    // Screenshot 
    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {

        String screenshotPath =
            Screenshots.captureScreenshot(getDriver(), result.getMethod().getMethodName());

        if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Passed");
            test.addScreenCaptureFromPath(screenshotPath);

        } else if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Failed: " + result.getThrowable().getMessage());
            test.addScreenCaptureFromPath(screenshotPath);

        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test Skipped");
        }
    }
    @AfterSuite
    public void flushReport() {
        ExtentManager.flushReport();
    }
    @AfterClass
    public void tearDown() {
        System.out.println("Test Cases Runned!");
    }
}