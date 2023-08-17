package automation.automation.orderscontroller;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class OrdersControllerPut {
	
	public Map<String, String> mHeaders = new HashMap<>();
	
	@BeforeMethod
	public void methodSetUp() {
		RestAssured.baseURI = "http://3.8.18.118:3000";
		mHeaders.put("content-type", "application/json");
	}

	@Test(priority = 0)
	public void testPUTOrdersController() {

		String putRequestBody = "{\r\n" + 
				"    \"orderNumber\": 2,\r\n" + 
				"    \"orderDate\": \"2019-12-07T00:00:00.000Z\",\r\n" + 
				"    \"requiredDate\": \"2019-12-07T00:00:00.000Z\",\r\n" + 
				"    \"shippedDate\": \"2019-12-07T00:00:00.000Z\",\r\n" + 
				"    \"status\": \"Delivered\",\r\n" + 
				"    \"comments\": \"waiting for delivery\",\r\n" + 
				"    \"customersCustomerNumber\": 0\r\n" + 
				"}";

		Response putOrdersController = RestAssured.given().headers(mHeaders).body(putRequestBody).put("/orders/2")
				.then().extract().response();

		System.out.println("Status code :" + putOrdersController.getStatusCode());

		System.out.println("Response :" + putOrdersController.asString());

		Response getOrderDetailsResponse = RestAssured.given().headers(mHeaders).get("/orders/" + 2).then().extract()
				.response();

		System.out.println("Order 2 response data is :" + getOrderDetailsResponse.asString());
		
		
		String status=JsonPath.parse(getOrderDetailsResponse.asString()).read("$.status").toString();
		
		Assert.assertEquals("Delivered", status);
	}
}
