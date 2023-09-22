package automation.dummytestcases;

import helper.HelperUtils;
import org.apache.log4j.Logger;

public class TestPropertyFile {
	private static final Logger logger = Logger.getLogger(TestPropertyFile.class);
	public static void main(String[] args) {
		logger.info("Value from properties file is :" + HelperUtils
				.getPropertyName("", "data.properties", "name"));

	}

}
