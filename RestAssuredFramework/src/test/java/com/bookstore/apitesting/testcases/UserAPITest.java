package com.bookstore.apitesting.testcases;

import com.bookstore.apitesting.base.BaseTest;
import com.bookstore.apitesting.client.RestClient;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import com.bookstore.apitesting.utils.JsonUtil;
import com.bookstore.apitesting.utils.ResponseValidator;

import java.io.IOException;

public class UserAPITest extends BaseTest {
	
	
    @Test
    public void testPostUser() throws IOException {
        String updatedBody = JsonUtil.updateJsonField("src/test/resources/createUser.json", "name", "Alice");
        Response response = RestClient.post("/api/users", updatedBody);
        ResponseValidator.logResponse(response);
        ResponseValidator.validateStatusCode(response, 201);
        ResponseValidator.validateField(response, "name", "Alice");
    }

    @Test
    public void testGetUser() {
        Response response = RestClient.get("/api/users/2");
        ResponseValidator.logResponse(response);
        ResponseValidator.validateStatusCode(response, 200);
        ResponseValidator.validateField(response, "data.id", "2");
    }

	/*
	 * @Test public void testPutUser() throws IOException { String updatedBody =
	 * JsonUtil.updateJsonField("src/test/resources/updateUser.json", "job",
	 * "Manager"); Response response = RestClient.put("/api/users/2", updatedBody);
	 * ResponseValidator.logResponse(response);
	 * ResponseValidator.validateStatusCode(response, 200); }
	 * 
	 * @Test public void testPatchUser() throws IOException { String updatedBody =
	 * JsonUtil.updateJsonField("src/test/resources/updateUser.json", "job", "CTO");
	 * Response response = RestClient.patch("/api/users/2", updatedBody);
	 * ResponseValidator.logResponse(response);
	 * ResponseValidator.validateStatusCode(response, 200); }
	 * 
	 * @Test public void testDeleteUser() { Response response =
	 * RestClient.delete("/api/users/2"); ResponseValidator.logResponse(response);
	 * ResponseValidator.validateStatusCode(response, 204); }
	 */
}