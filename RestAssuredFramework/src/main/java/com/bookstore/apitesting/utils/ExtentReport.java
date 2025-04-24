package com.bookstore.apitesting.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {
    private static ExtentReports extent;
    private static ExtentTest test;
    private static ExtentSparkReporter htmlReporter;

    public static void initializeReport(String reportPath) {
        htmlReporter = new ExtentSparkReporter(reportPath);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("Automation Test Report");
        htmlReporter.config().setReportName("API and UI Testing");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    public static ExtentTest createTest(String testName, String description) {
        test = extent.createTest(testName, description);
        return test;
    }

    public static void flushReport() {
        extent.flush();
    }
}