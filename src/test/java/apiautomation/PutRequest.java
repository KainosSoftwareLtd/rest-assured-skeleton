package apiautomation;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PutRequest {

	@Test
	public void testPutRequest() {

		RestAssured.baseURI = "http://xxxxxxx/";

		String requestBody = "{\"customerLoginRequest\":{\"username\":\"xxxxx\",\"password\":\"Passxxxxx\"}}";

		Map<String, String> headersMap = new HashMap<>();

		headersMap.put("Content-Type", "application/json");

		Response response = RestAssured.given().body(requestBody).headers(headersMap).when().post("/customerLogin")
				.then().extract().response();

		String session = JsonPath.parse(response.asString()).read("$.customerLoginResponse.sessionid").toString();

		String productBody = "{\"addProductRequest\":{\"productId\": \"30005\",\"productName\": \"Laptop2\",\"productPrice\": \"70000\",\"sessionId\":\""
				+ session + "\"" + "}}";

		Response putResponse = RestAssured.given().headers(headersMap).body(productBody).put("addProduct").then()
				.extract().response();

		System.out.println("Put Response is :" + putResponse.asString());
		
		
		/*String statusMessage=JsonPath.parse(putResponse.asString()).read("$.addProductResponse.statusMessage").toString();
		
		Assert.assertEquals("Product added successfully!", statusMessage);
		
		Response getResponse = RestAssured.given().when().get("/getProduct?productId=30005").then().extract().response();
		
		System.out.println("Product details are :"+getResponse.asString());*/

	}

}
