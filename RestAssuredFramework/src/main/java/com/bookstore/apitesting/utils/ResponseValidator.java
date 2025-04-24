package com.bookstore.apitesting.utils;

import io.restassured.response.Response;

import java.util.List;

import org.testng.Assert;

public class ResponseValidator {

    public static void validateStatusCode(Response response, int expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode, "Status code mismatch!");
    }

    public static void validateField(Response response, String jsonPath, String expectedValue) {
        Assert.assertEquals(response.jsonPath().getString(jsonPath), expectedValue, "Field validation failed!");
    }

    public static void logResponse(Response response) {
        System.out.println("Response:\n" + response.getBody().prettyPrint());
    }
    
    public static String extractValueFromResponse(Response response, String path) {
    	return response.jsonPath().getString(path);
    }
    
    public static void validateFieldPresence(Response response, String jsonPath, String expectedValue) {
        // Extract the list of values for the given JSONPath
        List<String> values = response.jsonPath().getList(jsonPath);

        // Assert that the list contains the expected value
        Assert.assertTrue(values.contains(expectedValue), 
            "Validation failed! '" + expectedValue + "' not found in '" + jsonPath + "'.");
    }


}