package automation.automation.orderscontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import automation.dummytestcases.TestPropertyFile;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class OrdersControllerDelete {
	private static final Logger logger = Logger.getLogger(OrdersControllerDelete.class);
	public Map<String, String> mHeaders = new HashMap<>();

	@BeforeMethod
	public void methodSetUp() {
		RestAssured.baseURI = "";
		mHeaders.put("content-type", "application/json");
	}

	@Test(priority = 0)
	public void testDeleteOrdersController() {
		Response putDeleteController = RestAssured.given().headers(mHeaders).delete("/orders/9")
				.then().extract().response();


		logger.info("Status code is :"+putDeleteController.getStatusCode());
		
		Response getTotalOrdersResponse = RestAssured.given().headers(mHeaders).get("orders").then().extract()
				.response();
		
		List<Integer> dataList=JsonPath.parse(getTotalOrdersResponse.asString()).read("$.[*].orderNumber");
		
		//dataList.forEach(x->System.out.println(x));
		
		dataList.forEach(System.out::println);

		logger.info(dataList.contains(9));
		
	}

}
