package automation.automation.orderscontroller;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;


public class OrdersGetController {

	public Map<String, String> mHeaders = new HashMap<>();

	@BeforeMethod
	public void methodSetUp() {
		RestAssured.baseURI = "";
		mHeaders.put("content-type", "application/json");
	}

	@Test(priority = 0)
	public void getOrdersCount() {

		Response getCountResponse = RestAssured.given().headers(mHeaders).get("orders/count").then().extract()
				.response();

		System.out.println("Count response is :" + getCountResponse.asString());

		Assert.assertEquals(200, getCountResponse.getStatusCode());

	}

	@Test(priority = 1, dependsOnMethods = "getOrdersCount")
	public void getTotalOrders() {
		Response getTotalOrdersResponse = RestAssured.given().headers(mHeaders).get("orders").then().extract()
				.response();

		System.out.println("Total Order :" + getTotalOrdersResponse.asString());

		Assert.assertEquals(200, getTotalOrdersResponse.getStatusCode());

	}

	@Test(priority = 2, dependsOnMethods = "getTotalOrders")
	public void getOrderDetails() {
		Response getOrderDetailsResponse = RestAssured.given().headers(mHeaders).get("orders/2").then().extract()
				.response();

		System.out.println("OrderDetails :" + getOrderDetailsResponse.asString());

		Assert.assertEquals(200, getOrderDetailsResponse.getStatusCode());

		Assert.assertEquals("2023-11-23T00:00:00.000Z",
				JsonPath.parse(getOrderDetailsResponse.asString()).read("$.orderDate").toString());
	}

}
