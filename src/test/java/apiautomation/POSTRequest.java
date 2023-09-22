package apiautomation;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class POSTRequest {
	private static final Logger logger = Logger.getLogger(POSTRequest.class);
	@Test(enabled = false)
	public void testPostRequest() {

		RestAssured.baseURI = "http://xxxxxx/";

		String requestBody = "{\"customerLoginRequest\":{\"username\":\"xxxxx\",\"password\":\"Passxxxxx\"}}";

		Map<String, String> headersMap = new HashMap<>();

		headersMap.put("Content-Type", "application/json");

		Response response = RestAssured.given().body(requestBody).headers(headersMap).when().post("/customerLogin")
				.then().extract().response();

		logger.info("Response is :" + response.asString());

		logger.info("Status code is :" + response.statusCode() + " and status line is :" + response.statusLine());

		logger.info(
				"Session id is :" + JsonPath.parse(response.asString()).read("$.customerLoginResponse.sessionid"));

		logger.info("Status  Message is :"
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

		logger.info("Response is :" + response.asString());

		logger.info("Status code is :" + response.statusCode() + " and status line is :" + response.statusLine());

		logger.info(
				"Session id is :" + JsonPath.parse(response.asString()).read("$.customerLoginResponse.sessionid"));

		logger.info("Status  Message is :"
				+ JsonPath.parse(response.asString()).read("$.customerLoginResponse.statusMessage"));
	}

}
