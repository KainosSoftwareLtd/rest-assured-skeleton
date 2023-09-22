package apiautomation;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import com.jayway.jsonpath.JsonPath;

import helper.HelperUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetRequestTest {
	private static final Logger logger = Logger.getLogger(GetRequestTest.class);

	@BeforeClass
	public void setUp() {
		RestAssured.baseURI = HelperUtils.getPropertyName("E:\\apiautomation\\src\\test\\resources\\data",
				"data.properties", "URI");
	}

	@Test
	public void testGetRequest() {

		Response response = RestAssured.given().when().get("/getProduct?productId=1007").then().extract().response();
		logger.info(response.asString());
		logger.info("Status code is :" + response.statusCode());
		Assert.assertEquals(200, response.statusCode());
		logger.info("Status Line is :" + response.statusLine());
	}

	@Test
	public void testQueryParam() {

		Response response = RestAssured.given().queryParam("productId", "1007").when().get("/getProduct").then()
				.extract().response();

		logger.info(response.asString());

		logger.info("Product Price is :"
				+ JsonPath.parse(response.asString()).read("$.getProductResponse.productPrice").toString());

		logger.info("Product Name is :"
				+ JsonPath.parse(response.asString()).read("$.getProductResponse.productName").toString());

		logger.info("Product Id is :"
				+ JsonPath.parse(response.asString()).read("$.getProductResponse.productId").toString());

	}

}
