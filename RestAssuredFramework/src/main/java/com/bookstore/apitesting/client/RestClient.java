package com.bookstore.apitesting.client;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.util.Map;

import io.restassured.RestAssured;

public class RestClient {

	private static final Logger LOGGER = Logger.getLogger(RestClient.class.getName());
	public static Response get(String endpoint) {
        try {
        	String fullURL = RestAssured.baseURI + endpoint;
        	LOGGER.info("Request URL: " + fullURL);
            return RestAssured.given()
            		.header("Content-Type", "application/json")
            		.get(fullURL);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,"Error during GET request: " + e.getMessage(), e);
            return null; 
        }
    }
	
	public static Response get(String endpoint,Map<String, Object> queryParams) {
        try {
            // Build the request
            RequestSpecification request = RestAssured.given()
                .header("Content-Type", "application/json")
                .queryParams(queryParams);
            
            String fullURL = RestAssured.baseURI + endpoint; // Combine base URI with endpoint
            LOGGER.info("Request URL: " + fullURL);
            return request.get(fullURL);
       
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error during GET request: " + e.getMessage(), e);
            return null;
        }
    }

    public static Response post(String endpoint, String body) {
        try {
            return RestAssured.given()
                    .header("Content-Type", "application/json")
                    .body(body)
                    .post(endpoint);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error during POST request: " + e.getMessage(), e);
            return null;
        }
    }

    public static Response post(String endpoint, String body, String token) {
        try {
            return RestAssured.given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .body(body)
                    .post(endpoint);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error during POST request with token: " + e.getMessage(), e);
            return null;
        }
    }

    public static Response put(String endpoint, String body, String token) {
        try {
            return RestAssured.given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .body(body)
                    .put(endpoint);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error during PUT request: " + e.getMessage(), e);
            return null;
        }
    }

    public static Response patch(String endpoint, String body, String token) {
        try {
            return RestAssured.given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .body(body)
                    .patch(endpoint);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error during PATCH request: " + e.getMessage(), e);
            return null;
        }
    }

    public static Response delete(String endpoint, String token) {
        try {
            return RestAssured.given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .when()
                    .delete(endpoint);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error during DELETE request: " + e.getMessage(), e);
            return null;
        }
    }

}