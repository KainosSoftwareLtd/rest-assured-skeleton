package apiautomation;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class POSTRequest {

	@Test(enabled = false)
	public void testPostRequest() {

		RestAssured.baseURI = "http://xxxxxx/";

		String requestBody = "{\"customerLoginRequest\":{\"username\":\"vinay\",\"password\":\"VinayPass\"}}";

		Map<String, String> headersMap = new HashMap<>();

		headersMap.put("Content-Type", "application/json");

		Response response = RestAssured.given().body(requestBody).headers(headersMap).when().post("/customerLogin")
				.then().extract().response();

		System.out.println("Response is :" + response.asString());

		System.out
				.println("Status code is :" + response.statusCode() + " and status line is :" + response.statusLine());

		System.out.println(
				"Session id is :" + JsonPath.parse(response.asString()).read("$.customerLoginResponse.sessionid"));

		System.out.println("Status  Message is :"
				+ JsonPath.parse(response.asString()).read("$.customerLoginResponse.statusMessage"));
	}

	@Test
	public void testPostRequest1() {

		RestAssured.baseURI = "http://xxxxx/";

		Map<String, String> headersMap = new HashMap<>();

		headersMap.put("Content-Type", "application/json");

		Response response = RestAssured.given()
				.body(new File("E:\\apiautomation\\src\\test\\resources\\data\\LoginRequestBody.json"))
				.headers(headersMap).post("/customerLogin").then().extract().response();

		System.out.println("Response is :" + response.asString());

		System.out
				.println("Status code is :" + response.statusCode() + " and status line is :" + response.statusLine());

		System.out.println(
				"Session id is :" + JsonPath.parse(response.asString()).read("$.customerLoginResponse.sessionid"));

		System.out.println("Status  Message is :"
				+ JsonPath.parse(response.asString()).read("$.customerLoginResponse.statusMessage"));
	}

}