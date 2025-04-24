package com.bookstore.apitesting.base;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentTest;
import com.bookstore.apitesting.client.RestClient;
import com.bookstore.apitesting.utils.ExtentReport;
import com.bookstore.apitesting.utils.JsonUtil;
import com.bookstore.apitesting.utils.ResponseValidator;

import java.lang.reflect.Method;

    public class BaseTest {
    	public static String token = "";
        private ExtentTest test;

        @BeforeSuite
        public void setupSuite() {
            ExtentReport.initializeReport("TestNG-HTML-Report.html");
            RestAssured.baseURI = "https://bookstore.toolsqa.com";   
            token = GetToken();
        }

	
		 @BeforeMethod public void setupTest(Method method) { test =
		 ExtentReport.createTest(method.getName(), "Response Got Success.."); }
		
		 @AfterMethod
		    public void teardownTest(ITestResult result) {
		        // Log test result dynamically based on its status
		        if (result.getStatus() == ITestResult.SUCCESS) {
		            test.pass("Test Passed Successfully");
		        } else if (result.getStatus() == ITestResult.FAILURE) {
		            test.fail("Test Failed: " + result.getThrowable().getMessage());
		        } else if (result.getStatus() == ITestResult.SKIP) {
		            test.skip("Test Skipped: " + result.getThrowable().getMessage());
		        }
		    }

		/*
		 * @BeforeClass public void setup() { RestAssured.baseURI =
		 * "https://bookstore.toolsqa.com"; token = GetToken();
		 * 
		 * }
		 */
        
        @AfterSuite
        public void tearDownSuite() {
            ExtentReport.flushReport();
        }

        public static String GetToken() {
        	
        	String body = JsonUtil.readJsonFromFile("\\src\\test\\resources\\token.json");
            Response response = RestClient.post("/Account/v1/GenerateToken", body);
            ResponseValidator.logResponse(response);
            ResponseValidator.validateStatusCode(response, 200);
            String token = ResponseValidator.extractValueFromResponse(response, "$.token");
            return token;
        }

    }

