package com.tester.buyagift.lib;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Timer;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.support.ui.WebDriverWait;



public class WebDriverUtils {

	Boolean isInitialized = false;
	WebDriver driver = null;
	WebDriverWait wait;

	Utils utils;
	Timer timer = new Timer();

	public WebDriverUtils(Utils utils) {
		super();
		
		this.utils = utils;
	}

	public void init() throws IOException, InterruptedException {
		if (isInitialized == true) {
			return;
		}

		System.setProperty("webdriver.chrome.driver", utils.readProperty("chromeDriver"));
		driver = createWebDriver("chrome");
		driver.manage().window().maximize();
		Thread.sleep(3000);

		wait = new WebDriverWait(driver, Duration.ofSeconds(100));
		isInitialized = true;
	}
	
	public void quit()
	{
		driver.quit();
	}

	public void get(String path) throws IOException {
		driver.get(path);
	}

	public WebElement findElement(By path) {
		return driver.findElement(path);
	}

	public WebElement findElementById(String path) {
		return driver.findElement(By.id(path));
	}

	public WebElement findElementByPath(String path) {
		return driver.findElement(By.xpath(path));
	}
	
	public List<WebElement> findElementsByPath(String path) {
		return driver.findElements(By.xpath(path));
	}

	public void moveToElement(WebElement wb) {
		Actions action = new Actions(driver);
		action.moveToElement(wb).perform();
	}

	public void handleAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			System.out.println(alert.getText() + " Alert is Displayed");
			alert.dismiss();
		} catch (NoAlertPresentException ex) {
			System.out.println("Alert is NOT Displayed");
		}
	}

	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}
	
	  public static WebDriver createWebDriver(String browser) {
	        if (browser.toString().equalsIgnoreCase("firefox")) {

	            return new FirefoxDriver();
	        } else if (browser.toString().equalsIgnoreCase("ie")) {

	            return new InternetExplorerDriver();
	        } else if (browser.toString().equalsIgnoreCase("chrome")) {

	            return new ChromeDriver();
	        }
	        
	        throw new IllegalArgumentException("WebDriver type is not understood.");
	    }
	  
	  public WebDriverWait getWaitObj(int seconds)
	  {
		 return  new WebDriverWait(driver,Duration.ofSeconds(30));
		  
	  }
	  
	  public void takeScreenshot(String destFilePath) throws IOException
	  {
		  TakesScreenshot takeSS = ((TakesScreenshot)driver);
		  File srcFile = takeSS.getScreenshotAs(OutputType.FILE);
		  
		  File destFile = new File(destFilePath);
		  FileHandler.copy(srcFile, destFile);
		  
	  }
}
