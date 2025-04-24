package com.bookstore.apitesting.testcases;

import com.bookstore.apitesting.base.BaseTest;
import com.bookstore.apitesting.client.RestClient;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import com.bookstore.apitesting.utils.ResponseValidator;




public class GetAllBooks extends BaseTest {
	
	
	 @Test
	    public void testGetBooks() {
		 try
		 {
	        Response response = RestClient.get("/BookStore/v1/Books");
	        ResponseValidator.logResponse(response);
	        ResponseValidator.validateStatusCode(response, 200);
	        ResponseValidator.validateFieldPresence(response, "books.title", "Git Pocket Guide");
     } catch (Exception e) {
         // Handle unexpected errors during the GET request
         System.err.println("Error during GET request: " + e.getMessage());
         e.printStackTrace();
     }

	    }

 
}