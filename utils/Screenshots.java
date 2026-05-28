package com.utils;
 
 
import java.io.File;

import java.io.IOException;

import java.text.SimpleDateFormat;

import java.util.Date;
 
import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.WebDriver;
 
import com.google.common.io.Files;
 
 
public class Screenshots {
 
public static String captureScreenshot(WebDriver driver, String screenshotName) {
        String date = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") 
                     + "/target/Extent Report/Screenshots/" 
                     + screenshotName + "_" + date + ".png";
        File dest = new File(path);
        try {

            Files.copy(src, dest);
        } catch (IOException e) {

            e.printStackTrace();
        }
        return path;
    }
}