package apiautomation;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import helper.HelperUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetRequestTest {

	@BeforeClass
	public void setUp() {
		RestAssured.baseURI = HelperUtils.getPropertyName("E:\\apiautomation\\src\\test\\resources\\data",
				"data.properties", "URI");
	}

	@Test
	public void testGetRequest() {

		Response response = RestAssured.given().when().get("/getProduct?productId=1007").then().extract().response();
		System.out.println(response.asString());

		System.out.println("Status code is :" + response.statusCode());

		Assert.assertEquals(200, response.statusCode());

		System.out.println("Status Line is :" + response.statusLine());
	}

	@Test
	public void testQueryParam() {

		Response response = RestAssured.given().queryParam("productId", "1007").when().get("/getProduct").then()
				.extract().response();

		System.out.println(response.asString());

		System.out.println("Product Price is :"
				+ JsonPath.parse(response.asString()).read("$.getProductResponse.productPrice").toString());

		System.out.println("Product Name is :"
				+ JsonPath.parse(response.asString()).read("$.getProductResponse.productName").toString());

		System.out.println("Product Id is :"
				+ JsonPath.parse(response.asString()).read("$.getProductResponse.productId").toString());

	}

}
