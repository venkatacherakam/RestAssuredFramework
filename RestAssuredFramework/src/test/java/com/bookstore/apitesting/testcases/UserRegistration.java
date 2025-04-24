package com.bookstore.apitesting.testcases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.bookstore.apitesting.base.BaseTest;
import com.bookstore.apitesting.client.RestClient;
import com.bookstore.apitesting.utils.JsonUtil;
import com.bookstore.apitesting.utils.ResponseValidator;
import com.bookstore.apitesting.utils.commonUtil;

import io.restassured.response.Response;

public class UserRegistration extends BaseTest {
	
	
    @Test
    public void testUserRegistration() throws IOException {
    	
        String requestBody = JsonUtil.readJsonFromFile("\\src\\test\\resources\\createUser.json");
        String user_name = "venkat" + commonUtil.generateRandomInt(1, 9999);
        String updatedbody = JsonUtil.updateJsonField(requestBody,"userName", user_name);
        
        try {
        Response response = RestClient.post("/Account/v1/User", updatedbody, token);
        ResponseValidator.logResponse(response);
        ResponseValidator.validateStatusCode(response, 201);
        ResponseValidator.validateField(response, "username", user_name);
        } catch (Exception e) {
            // Handle unexpected errors during the GET request
            System.err.println("Error during GET request: " + e.getMessage());
            e.printStackTrace();
        }

    }

 
}