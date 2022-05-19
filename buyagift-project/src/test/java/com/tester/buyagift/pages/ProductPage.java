package com.tester.buyagift.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tester.buyagift.lib.Utils;
import com.tester.buyagift.lib.WebDriverUtils;

public class ProductPage {

	private WebDriverUtils driver;
	private WebDriverWait waitobj;
	
	By firstResult = By.xpath("//a[@data-re='shortbreaks_2nights-_-product_list-_-1-_-10919897']");
	By prodName = By.xpath("//span[@class='product-name']");
	By prodPrice = By.id("product-price-current");
	By buyNow = By.xpath("//button[contains(@class, 'top') and contains(text(),'Buy now')]");
	By costTitle = By.xpath("//span[@class='cost_title']");
	By bestPriceBanner = By.xpath("//div[@class='cv-bag001-L']");

	public ProductPage(WebDriverUtils webdriver) {
		super();
		this.driver = webdriver;
		waitobj = driver.getWaitObj(10);
	}

	public String[] getHotelNameandPricefromProductspage() throws InterruptedException {
		String[] prodDetails = new String[2];
		Thread.sleep(2000);
		driver.findElement(firstResult).click();
		waitobj.until(ExpectedConditions.presenceOfElementLocated(bestPriceBanner));
		waitobj.until(ExpectedConditions.elementToBeClickable(driver.findElement(buyNow)));
	
		prodDetails[0] = driver.findElement(prodName).getText();

		String price = driver.findElement(prodPrice).getText();
		price = price.substring(1);
		prodDetails[1] = price;

		return prodDetails;
	}

	public void invokeBuyNow() throws InterruptedException {
		Thread.sleep(5000);
		driver.findElement(buyNow).click();
		waitobj.until(ExpectedConditions.presenceOfElementLocated(costTitle));
	}

}
