package apiautomation;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CreateJSONBody {
	private static final Logger logger = Logger.getLogger(GetRequestTest.class);
	@Test
	public void createJSON() {

		RestAssured.baseURI = "http://xxxxx/";

		Map<String, String> headersMap = new HashMap<>();

		headersMap.put("Content-Type", "application/json");

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("username", "xxxx");
		jsonObject.put("password", "Passxxx");

		JSONObject parentJsonObject = new JSONObject();
		parentJsonObject.put("customerLoginRequest", jsonObject);

		logger.info(parentJsonObject);

		Response response = RestAssured.given().body(parentJsonObject.toString()).headers(headersMap).when()
				.post("/customerLogin").then().log().ifStatusCodeIsEqualTo(500).extract().response();

		logger.info("Response is :" + response.asString());

		logger.info("Status code is :" + response.statusCode() + " and status line is :" + response.statusLine());

		logger.info(
				"Session id is :" + JsonPath.parse(response.asString()).read("$.customerLoginResponse.sessionid"));

		logger.info("Status  Message is :"
				+ JsonPath.parse(response.asString()).read("$.customerLoginResponse.statusMessage"));

	}

	@Test(enabled=false)
	public void test() {
		RestAssured.baseURI = "http://xxxxx/";

		Map<String, String> headersMap = new HashMap<>();

		headersMap.put("Content-Type", "application/json");

		Response response = RestAssured.given().pathParam("username", "xxxx").pathParam("password", "Passxxxx")
				.headers(headersMap).when().post("/customerLogin/{username}/{password}").then().extract().response();

		logger.info("Response is :" + response.asString());
		
	}

}
