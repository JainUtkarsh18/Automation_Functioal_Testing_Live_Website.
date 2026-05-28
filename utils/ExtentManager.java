package com.utils;
 
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
 
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
 
public class ExtentManager {
    private static ExtentReports extent;
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    public static ExtentSparkReporter sparkReporter;
    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }
    public static void createInstance() {
 
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
 
        String reportPath = System.getProperty("user.dir")
                + "/target/Extent Report/Reports/TestReport_" + timestamp + ".html";
        sparkReporter = new ExtentSparkReporter(reportPath);
 
        // Report Configuration
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("Functional Automation Testing");
        sparkReporter.config().setDocumentTitle("Automation Execution Report");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        // System Info
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Tester", "Utkarsh Jain");
        extent.setSystemInfo("Browser", "Chrome");
    }
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}