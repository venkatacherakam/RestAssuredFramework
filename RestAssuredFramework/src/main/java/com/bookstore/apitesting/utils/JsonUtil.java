package com.bookstore.apitesting.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonUtil {
    public static String updateJsonField(String jsonString, String key, String value) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonString);
        if (root.has(key) && root instanceof ObjectNode) {
            ((ObjectNode) root).put(key, value);
        } else {
            throw new IllegalArgumentException("Key '" + key + "' does not exist in the JSON file.");
        }

        // Return the updated JSON as a string
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
    }

    

	public static String readJsonFromFile(String fileName) {
        try {
            // Construct the file path using the project directory
            Path filePath = Paths.get(System.getProperty("user.dir"), fileName);

            // Read all bytes from the file and convert to a string
            return new String(Files.readAllBytes(filePath));
        } catch (Exception e) {
            // Handle exceptions gracefully
            System.out.println("An error occurred while reading the file: " + e.getMessage());
            return null; // Return null if an error occurs
        }
	}

}