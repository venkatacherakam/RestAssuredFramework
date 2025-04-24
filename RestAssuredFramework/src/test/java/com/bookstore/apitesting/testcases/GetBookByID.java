package com.bookstore.apitesting.testcases;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.bookstore.apitesting.base.BaseTest;
import com.bookstore.apitesting.client.RestClient;
import com.bookstore.apitesting.utils.JsonUtil;
import com.bookstore.apitesting.utils.ResponseValidator;
import com.bookstore.apitesting.utils.commonUtil;

import io.restassured.response.Response;

public class GetBookByID extends BaseTest {
	
	
    @Test
    public void testGetBookByID() throws IOException {
    	String bookID = "9781449365035";

    	String endpoint = "/BookStore/v1/Books?ISBN="+bookID;
    	try {
            // Call the GET API method
            Response response = RestClient.get(endpoint);

            // Log the response for debugging purposes
            try {
                ResponseValidator.logResponse(response);
            } catch (Exception e) {
                System.err.println("Error logging response: " + e.getMessage());
            }

            // Validate the status code
            try {
                ResponseValidator.validateStatusCode(response, 200);
            } catch (AssertionError e) {
                System.err.println("Status code validation failed: " + e.getMessage());
                throw e; // Re-throw the exception to ensure TestNG reports it as a failure
            }

            // Validate the field in the response
            try {
                ResponseValidator.validateField(response, "books.isbn", bookID);
            } catch (AssertionError e) {
                System.err.println("Field validation failed: " + e.getMessage());
                throw e; // Re-throw to preserve the failure in the report
            }

        } catch (Exception e) {
            // Handle unexpected errors during the GET request
            System.err.println("Error during GET request: " + e.getMessage());
            e.printStackTrace();
        }

    }

 
}