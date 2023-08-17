package automation.dummytestcases;


import helper.HelperUtils;

public class TestPropertyFile {

	public static void main(String[] args) {
		System.out.println("Value from properties file is :" + HelperUtils
				.getPropertyName("", "data.properties", "name"));

	}

}
