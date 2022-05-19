package com.tester.buyagift_project;

import bsh.ParseException;
import junit.framework.TestCase;

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


public class BestSeller_Hotel extends TestBaseClass {
	String[] prodDetailsfromHomepage;
	String[] locationDetails = new String[2];

	public BestSeller_Hotel() throws IOException {
		super();
	}
	 
	@Test(priority = 1, groups = { "Test1" }, description = "Test Case 1: Get and check BestSeller hotel details")
	public void TestCase01_verifyBestSellerResult() throws IOException, InterruptedException {

		//String testCaseParameterHotelName = utils.readProperty("hotelName");
		prodDetailsfromHomepage = homepg.searchHotel("Two Night Hotel Break");

		webUtils.handleAlert();
		
		// Check that the first item returns is marked as “No 1 BestSeller”
		Assert.assertTrue(prodDetailsfromHomepage[0].equalsIgnoreCase("BESTSELLER"));

		// Check the name and price of the “No 1 BestSeller”
		String hotelTitle = utils.readProperty("hotelName");
		Assert.assertTrue(prodDetailsfromHomepage[1].equalsIgnoreCase(hotelTitle));
		Assert.assertTrue(prodDetailsfromHomepage[2].contains("99"));
	}

	@Test(priority = 2, groups = { "Test1" }, description = "Test Case 2 Step 1,2,3,4: Verify hotel name and price on product page")
	public void TestCase02_1_verifyNameAndPriceOnProductPage() throws InterruptedException {

		// Continue from test case 1

		String[] prodDetailsfromProductpage = prodpg.getHotelNameandPricefromProductspage();

		Assert.assertEquals(prodDetailsfromHomepage[1], prodDetailsfromProductpage[0]); // prod name
		Assert.assertEquals(prodDetailsfromHomepage[2], prodDetailsfromProductpage[1]); // prod price
	}

	@Test(priority = 3, groups = { "Test1" }, description = "Test Case 2 Step 5,6: Click BuyNow and Validate Payment Total")
	public void TestCase02_2_validatePaymentTotal() throws ParseException, InterruptedException {

		// Click Buy now
		prodpg.invokeBuyNow();

		String deliveryType = "Standard Delivery";

		String[] paymentTotal = checkoutpg.getPaymentTotal(deliveryType);

		Assert.assertEquals(prodDetailsfromHomepage[2], paymentTotal[1]);// basket total or product price

		// Add delivery total to basket total and verify it matches the total on cart
		// page

		double cartTotal = Double.parseDouble(paymentTotal[3]);
		double total = Double.parseDouble(paymentTotal[1]) + Double.parseDouble(paymentTotal[2]);

		Assert.assertTrue(cartTotal == total);
	}
}
