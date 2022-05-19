package com.tester.buyagift.pages;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import com.tester.buyagift.lib.Utils;
import com.tester.buyagift.lib.WebDriverUtils;

public class HomePage {
	private WebDriverUtils driver;
	private Utils utils;
	private WebDriverWait waitobj;
	String[] prodDetails = new String[5];

	By cookieDialog = By.xpath("//div[@class='ot-sdk-container']");
	By cookieAllowBtn = By.id("onetrust-accept-btn-handler");
	By searchbox = By.id("search-field");
	By firstResultBestSeller = By.xpath(
			"//a[@data-re='shortbreaks_2nights-_-product_list-_-1-_-10919897']//div[@class='productsash']//span");
	By hotelTitle = By.xpath(
			"//a[@data-re='shortbreaks_2nights-_-product_list-_-1-_-10919897']//div[2]//h3[@class='producttitle'][1]");
	By hotelPrice = By
			.xpath("//a[@data-re='shortbreaks_2nights-_-product_list-_-1-_-10919897']//div[@id='price-drop']//div");
	By spaDaysPageTitle = By.xpath("//div[@class='row']//h1");
	By spaDayFilter = By.xpath("//a[@id='reflink_PamperingType_0_Spaday']//label/input");
	By firstImgLoc = By.xpath("//div[@id='productlist-results']//img[contains(@data-src,'BestWesternSureBham')]");

	public HomePage(WebDriverUtils webdriver, Utils configutils) {
		super();
		driver = webdriver;
		utils = configutils;
		waitobj = driver.getWaitObj(10);
	}

	/*
	 * Used to open Home Page and Accept Cookies
	 */
	public void openPage() throws IOException, InterruptedException {
		driver.get(utils.readProperty("url"));
		Thread.sleep(5000);
		if (driver.findElement(cookieDialog).isDisplayed()) {
			driver.findElement(cookieAllowBtn).click();
		}
	}
	
	public String[] searchHotel(String hotelName) throws InterruptedException {

		// System.out.println("Hello");
		searchProduct(hotelName);
		waitobj.until(ExpectedConditions.visibilityOfElementLocated(firstImgLoc));
		
		// Search for “Two Night Hotel Break”
		String[] prodDetailsfromHomepage = getProductDetails();
		return prodDetailsfromHomepage;
	}

	/*
	 * Used to search a particular kind of hotel on home page
	 */
	public void searchProduct(String searchValue) {
		driver.findElement(searchbox).sendKeys(searchValue);

		driver.findElement(searchbox).sendKeys(Keys.ENTER);
	}

	public String[] getProductDetails() throws InterruptedException {
		Thread.sleep(5000);
		prodDetails[0] = driver.findElement(firstResultBestSeller).getText();
		prodDetails[1] = driver.findElement(hotelTitle).getText();

		String price = driver.findElement(hotelPrice).getText();
		price = price.substring(1);
		prodDetails[2] = price;

		return prodDetails;
	}

	/*
	 * Used to select menuItem on home page
	 */
	public void selectMenuItem(String mainMenu, String subMenuItem, String clickableItem) {
		WebElement menuWb = driver.findElementByPath(
				"//ul[@class='cd-dropdown-content scrollable level_1']//li[contains(@data-text,'" + mainMenu + "')]");
		driver.moveToElement(menuWb);
		menuWb = driver
				.findElementByPath("//ul[@class='cd-dropdown-content scrollable level_1']//ul//li[contains(@data-text,'"
						+ subMenuItem + "')]");
		driver.moveToElement(menuWb);
		menuWb = driver.findElementByPath("//li[contains(@data-text,'" + subMenuItem
				+ "')]//ul//li//a[@data-re = 'sitewide-_-nav-_-spa__beauty-_-spa_days']/following-sibling::ul//li//a[contains(text(),'"
				+ clickableItem + "')]");
		menuWb.click();
		waitobj.until(ExpectedConditions.visibilityOf(driver.findElement(spaDaysPageTitle)));
	
	}

	public String[] getSpaPageDetails() {
		String[] spaPageDetails = new String[2];
		spaPageDetails[0] = driver.getCurrentURL();
		spaPageDetails[1] = driver.findElement(spaDaysPageTitle).getText();

		return spaPageDetails;
	}

	public String[] getFilterDetails(String location) {
		String[] filterDetails = new String[2];
		filterDetails[0] = driver.findElement(spaDayFilter).getAttribute("checked");
		filterDetails[1] = driver.findElement(getSpaDayLocationFilterPath(location)).getAttribute("checked");

		return filterDetails;
	}

	private By getSpaDayLocationFilterPath(String location) {
		return By.xpath("//a[@id='reflink_Region_0_" + location + "']//label/input");
	}
}
