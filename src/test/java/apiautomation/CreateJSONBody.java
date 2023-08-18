package apiautomation;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CreateJSONBody {

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

		System.out.println(parentJsonObject);

		Response response = RestAssured.given().body(parentJsonObject.toString()).headers(headersMap).when()
				.post("/customerLogin").then().log().ifStatusCodeIsEqualTo(500).extract().response();

		System.out.println("Response is :" + response.asString());

		System.out
				.println("Status code is :" + response.statusCode() + " and status line is :" + response.statusLine());

		System.out.println(
				"Session id is :" + JsonPath.parse(response.asString()).read("$.customerLoginResponse.sessionid"));

		System.out.println("Status  Message is :"
				+ JsonPath.parse(response.asString()).read("$.customerLoginResponse.statusMessage"));

	}

	@Test(enabled=false)
	public void test() {
		RestAssured.baseURI = "http://xxxxx/";

		Map<String, String> headersMap = new HashMap<>();

		headersMap.put("Content-Type", "application/json");

		Response response = RestAssured.given().pathParam("username", "vinay").pathParam("password", "VinayPass")
				.headers(headersMap).when().post("/customerLogin/{username}/{password}").then().extract().response();

		System.out.println("Response is :" + response.asString());
		
	}

}
