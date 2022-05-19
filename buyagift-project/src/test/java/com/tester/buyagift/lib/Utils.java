package com.tester.buyagift.lib;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Utils {

	Properties prop = new Properties();

	public String readProperty(String propKey) throws IOException {
		String value = "";
		FileReader reader = new FileReader("local.properties");

		prop.load(reader);
		if (propKey.contains("url"))
			value = prop.getProperty("url");
		if (propKey.contains("chromeBrowser"))
			value = prop.getProperty("chrome.binary");
		if (propKey.contains("chromeDriver"))
			value = prop.getProperty("chrome.driver.location");
		if (propKey.contains("firefoxBrowser"))
			value = prop.getProperty("firefox.binary");
		if (propKey.contains("geckoDriver"))
			value = prop.getProperty("firefox.driver.location");
		if (propKey.contains("hotelName"))
			value = prop.getProperty("testHotelParameter");

		return value;
	}

}
