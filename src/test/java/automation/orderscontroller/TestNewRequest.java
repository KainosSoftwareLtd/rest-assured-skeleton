package automation.automation.orderscontroller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestNewRequest {

	String password;

	String email;

	@Test
	public void test() {
		RestAssured.baseURI = "";

		Map<String, String> mHeaders = new HashMap<>();
		mHeaders.put("Content-Type", "application/json");

		JSONObject json = new JSONObject();
		email = generateRandomAphaNumericString(7) + "@gmail.com";
		json.put("email", email);
		password = generateRandomAphaNumericString(9);
		json.put("password", password);
		json.put("firstName", generateRandomString(5));
		json.put("lastName", generateRandomString(6));
		json.put("phone", generateRandomNumber(10));
		json.put("addressLine1", generateRandomString(8));
		json.put("addressLine2", generateRandomString(6));
		json.put("postCode", generateRandomAphaNumericString(7));
		json.put("country", generateRandomString(4));
		json.put("creditLimit", Integer.parseInt(generateRandomNumber(8)));

		Response signUp = RestAssured.given().headers(mHeaders).body(json.toString()).when().post("/customer/signup")
				.then().extract().response();
		System.out.println(signUp.asString());

		JSONObject loginRequestBody = new JSONObject();
		loginRequestBody.put("email", email);
		loginRequestBody.put("password", password);
		Response loginResponse = RestAssured.given().headers(mHeaders).body(loginRequestBody.toString()).when()
				.post("/customer/login").then().extract().response();
		
		System.out.println(loginResponse.asString());

		String tokenString = JsonPath.parse(loginResponse.asString()).read("$.token").toString();
		
		System.out.println("Token is :"+tokenString);
		mHeaders.put("Authorization", "Bearer " + tokenString);

		Response getResponse = RestAssured.given().headers(mHeaders).body("").when().get("/customers/me").then()
				.extract().response();

		System.out.println(getResponse.asString());
	}

	private static String generateRandomString(final int length) {
		return RandomStringUtils.randomAlphabetic(length).toLowerCase();
	}

	private static String generateRandomAphaNumericString(final int length) {
		return RandomStringUtils.randomAlphanumeric(length).toLowerCase();
	}

	private static String generateRandomNumber(final int length) {
		return RandomStringUtils.randomNumeric(length);
	}
}
