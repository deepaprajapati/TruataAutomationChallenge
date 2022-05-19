package com.tester.buyagift.pages;

import org.openqa.selenium.By;

import com.tester.buyagift.lib.Utils;
import com.tester.buyagift.lib.WebDriverUtils;

public class CheckoutPage {

	private WebDriverUtils driver;

	By stdDeliveryCharge = By.xpath("//select[@id='basketDeliverySelect']//option");
	By basketTotal = By.xpath("//div[@class='prd_holder']//div[@class='row basket_totals']//span[2]");
	By deliveryTotal = By.xpath("//div[@class='prd_holder']//div[@class='delivery_totals']//span[2]");
	By finalTotal = By.xpath("//div[@class='prd_holder']//div[@class='row final_totals']//span[2]");

	public CheckoutPage(WebDriverUtils webdriver) {
		super();

		this.driver = webdriver;
	}

	public String[] getPaymentTotal(String deliveryCharge) {
		String[] paymentDetails = new String[4];

		if (deliveryCharge.contains("Standard Delivery")) {
			paymentDetails[0] = "2.99";
		} else if (deliveryCharge.contains("Next Day Delivery")) {
			paymentDetails[0] = "7.50";
		} else {
			paymentDetails[0] = "0";
		}

		String basket = driver.findElement(basketTotal).getText();
		if (basket.contains(".00")) {
			basket = basket.substring(1, basket.indexOf('.'));
		}
		paymentDetails[1] = basket;
		System.out.println(basket);

		String delTotal = driver.findElement(deliveryTotal).getText();
		delTotal = delTotal.substring(1);

		paymentDetails[2] = delTotal;
		System.out.println(delTotal);

		String payTotal = driver.findElement(finalTotal).getText();
		payTotal = payTotal.substring(1);
		paymentDetails[3] = payTotal;
		System.out.println(payTotal);

		return paymentDetails;
	}

}
