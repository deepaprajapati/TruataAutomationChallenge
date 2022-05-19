package com.tester.buyagift_project;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.tester.buyagift.lib.Utils;
import com.tester.buyagift.lib.WebDriverUtils;
import com.tester.buyagift.pages.CheckoutPage;
import com.tester.buyagift.pages.HomePage;
import com.tester.buyagift.pages.ProductPage;

import junit.framework.TestCase;

public class Location_Spa extends TestBaseClass {

	String[] locationDetails = new String[2];

	public Location_Spa() throws IOException {
		super();
	}
	

	@Test(priority = 1, groups = { "Test2" }, description = "Test Case 3 Step 1,2:Select Experiences -> Spa -> London")
	public void TestCase03_1_selectLocation() throws InterruptedException {
		String location = "London";

		homepg.selectMenuItem("Experiences", "Spa", location);
	
		webUtils.handleAlert();
	}

	@Test(priority = 2, groups = { "Test2" }, description = "Test Case 3 Step 3: Validate London Spa page and filter")
	public void TestCase03_2_validateLocationSpaDetails() throws InterruptedException {
		String location = "London";
		String spaDaysPageTitle = "London Spa Days";

		locationDetails = homepg.getSpaPageDetails();
		Assert.assertTrue(locationDetails[0].contains("spa-and-beauty/spa-day/" + location.toLowerCase()));
		Assert.assertTrue(locationDetails[1].contains(spaDaysPageTitle));

		locationDetails = homepg.getFilterDetails(location);
		Assert.assertTrue(locationDetails[0].contains("true"));
		Assert.assertTrue(locationDetails[1].contains("true"));
	}

}
