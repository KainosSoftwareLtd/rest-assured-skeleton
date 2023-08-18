package automation.automation.orderscontroller;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class OrdersControllerPost {

	public Map<String, String> mHeaders = new HashMap<>();

	@BeforeMethod
	public void methodSetUp() {
		RestAssured.baseURI = "http://xxxxxxx";
		mHeaders.put("content-type", "application/json");
	}

	@Test(priority = 0)
	public void testPOSTOrdersController() {

		String requestBody = "{\r\n" + " \r\n" + "  \"orderDate\": \"2023-12-09T13:59:55.430Z\",\r\n"
				+ "  \"requiredDate\": \"2023-11-07T13:59:55.430Z\",\r\n"
				+ "  \"shippedDate\": \"2023-12-07T13:59:55.430Z\",\r\n" + "  \"status\": \"delivered\",\r\n"
				+ "  \"comments\": \"delivered to customer\",\r\n" + "  \"customersCustomerNumber\": 4567865678\r\n"
				+ "\r\n" + "}";

		Response postOrdersController = RestAssured.given().headers(mHeaders).body(requestBody).post("/orders").then()
				.extract().response();

		System.out.println("PSOT Orders Controller  response is :" + postOrdersController.asString());

		Assert.assertEquals(200, postOrdersController.getStatusCode());

		int orderNumber = Integer
				.parseInt(JsonPath.parse(postOrdersController.asString()).read("$.orderNumber").toString());

		Response getOrderDetailsResponse = RestAssured.given().headers(mHeaders).get("/orders/" + orderNumber).then()
				.extract().response();

		System.out.println("New Order Number Get response is :" + getOrderDetailsResponse.asString());

		Assert.assertEquals(orderNumber,
				Integer.parseInt(JsonPath.parse(getOrderDetailsResponse.asString()).read("$.orderNumber").toString()));

	}

}
