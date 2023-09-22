package apiautomation;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class LogRequestResponse {
	private static final Logger logger = Logger.getLogger(LogRequestResponse.class);
	
	@Test
	public void test() {
		RestAssured.baseURI = "http://xxxxxx/";

		Response response = RestAssured
				.given()
				.log()
				.body()
				.queryParam("productId", "1007")
				.when()
				.get("/getProduct")
				.then()
				.log()
				.body()
				.extract()
				.response();

		logger.info(response.asString());
		
		
		
		//given().log().all()  ......   //log all request specification details
		
		//.params() .....///log only the parmeters
		
		//.body()  ...//log only body paraemters
		
		//.headers()
		
		//.cookies()
		
		//.method()
	}
	
	@Test
	public void test2() {
		RestAssured.baseURI = "http://xxxxx/";

		String requestBody = "{\"customerLoginRequest\":{\"username\":\"xxxxx\",\"password\":\"Passxxxxx\"}}";

		Map<String, String> headersMap = new HashMap<>();

		headersMap.put("Content-Type", "application/json");

		Response response = RestAssured
				.given()
				.log()
				.body()
				.body(requestBody)
				.headers(headersMap)
				.when()
				.post("/customerLogin")
				.then()
				.log()
				.body()
				.extract()
				.response();

		logger.info("Response is :" + response.asString());
	}
	
}
