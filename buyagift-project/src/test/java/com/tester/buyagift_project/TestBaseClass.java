package com.tester.buyagift_project;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import com.tester.buyagift.lib.Utils;
import com.tester.buyagift.lib.WebDriverUtils;
import com.tester.buyagift.pages.CheckoutPage;
import com.tester.buyagift.pages.HomePage;
import com.tester.buyagift.pages.ProductPage;

import junit.framework.TestCase;

abstract class TestBaseClass extends TestCase {
	WebDriverUtils webUtils;
	HomePage homepg;
	ProductPage prodpg;
	CheckoutPage checkoutpg;
	Utils utils = new Utils();
	
	public TestBaseClass()
	{
		super();
	}
	
	@AfterMethod
	public void screenshots(ITestResult result) throws IOException
	{
		Date date = new Date();
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
	    String ssformat = dateFormat.format(date);
		if(ITestResult.FAILURE == result.getStatus())
		{
			System.out.println(result.getName());
			webUtils.takeScreenshot("./target/Screenshots/FAILURE_"+result.getName()+ssformat+".png");
		}
		if(ITestResult.SUCCESS == result.getStatus())
		{
			System.out.println(result.getName());
			webUtils.takeScreenshot("./target/Screenshots/SUCCESS_"+result.getName()+ssformat+".png");
		}
	}

	 @BeforeClass
	    public void setUpTests() throws IOException, InterruptedException {
		 	
	    	webUtils = new WebDriverUtils(utils);
			webUtils.init();
			
			homepg = new HomePage(webUtils, utils);
	    	homepg.openPage();
	    	
			prodpg = new ProductPage(webUtils);
			checkoutpg = new CheckoutPage(webUtils);
	    }
	 
		@AfterClass
	    public void stopBrowser(){
			webUtils.quit();
	    }
}
