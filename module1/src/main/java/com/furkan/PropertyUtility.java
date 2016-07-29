package com.furkan;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtility {
	String result = "";
	InputStream inputStream;

	public String getPropertyValues() throws IOException {
		try {
			Properties properties = new Properties();
			// local file path for properties file
			String propertyPath = "/home/spootrick/config/config.properties";
			inputStream = new FileInputStream(propertyPath);

			if (inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new FileNotFoundException("Property file not found: " + propertyPath);
			}

			// getting the property values from the properties file
			String user = properties.getProperty("bamboo.inject.user");
			String workingOn = properties.getProperty("bamboo.inject.working-on");

			result = "User: " + user + " is currently working on " + workingOn;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			inputStream.close();
		}
		return result;
	}
}

