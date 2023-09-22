package automation.automation.orderscontroller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class OrdersControllerPut {
	private static final Logger logger = Logger.getLogger(OrdersControllerPut.class);
	public Map<String, String> mHeaders = new HashMap<>();
	
	@BeforeMethod
	public void methodSetUp() {
		RestAssured.baseURI = "http://xxxxxx";
		mHeaders.put("content-type", "application/json");
	}

	@Test(priority = 0)
	public void testPUTOrdersController() {

		String putRequestBody = "{\r\n" + 
				"    \"orderNumber\": 2,\r\n" + 
				"    \"orderDate\": \"2023-12-07T00:00:00.000Z\",\r\n" + 
				"    \"requiredDate\": \"2023-12-07T00:00:00.000Z\",\r\n" + 
				"    \"shippedDate\": \"2023-12-07T00:00:00.000Z\",\r\n" + 
				"    \"status\": \"Delivered\",\r\n" + 
				"    \"comments\": \"waiting for delivery\",\r\n" + 
				"    \"customersCustomerNumber\": 0\r\n" + 
				"}";

		Response putOrdersController = RestAssured.given().headers(mHeaders).body(putRequestBody).put("/orders/2")
				.then().extract().response();

		logger.info("Status code :" + putOrdersController.getStatusCode());

		logger.info("Response :" + putOrdersController.asString());

		Response getOrderDetailsResponse = RestAssured.given().headers(mHeaders).get("/orders/" + 2).then().extract()
				.response();

		logger.info("Order 2 response data is :" + getOrderDetailsResponse.asString());
		
		
		String status=JsonPath.parse(getOrderDetailsResponse.asString()).read("$.status").toString();
		
		Assert.assertEquals("Delivered", status);
	}
}
