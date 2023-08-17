package helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class HelperUtils {

	static Properties properties;

	public static String getPropertyName(final String filePath, final String fileName, final String key) {

		try (InputStream inputStream = new FileInputStream(new File(filePath + File.separator + fileName))) {
			
			properties=new Properties();
			properties.load(inputStream);

		} catch (Exception e) {
			System.out.println("Unable to read file :" + e.getMessage());
		}

		return properties.getProperty(key);

	}

}
