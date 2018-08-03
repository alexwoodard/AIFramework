package Runner;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import chrome.ChromeDriverEx;
import clicker.Clicker;
import coverage.fullpage.FullPageScreenshotBuilder;

public class Runner {

	public static void main(String args[]) throws Exception {
		
		String pageUnderTest = "https://www.godaddy.com/websites/website-builder";

		String gridAddress = "http://10.32.64.63:4444/wd/hub";
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		ChromeDriver rwd =new ChromeDriver();
//		RemoteWebDriver rwd = new RemoteWebDriver(new URL(gridAddress), capability);
		Clicker crawler = new Clicker();
		rwd.get(pageUnderTest);
		rwd.manage().window().maximize();
		Dimension d = new Dimension(1000, 1000);
		rwd.manage().window().setSize(d);
		synchronized (rwd) {
			rwd.wait(5000);
		}

		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", false);
		options.addArguments("disable-infobars");
		ChromeDriverEx driver = new ChromeDriverEx(options);
		driver.get(pageUnderTest);
		driver.manage().window().setSize(new Dimension(1000,1000));
		synchronized(driver){
			driver.wait(3000);
		}
		FullPageScreenshotBuilder fpsb = new FullPageScreenshotBuilder();
		fpsb.build(driver, "images/fullpage/fullpage.png",1000);
		driver.close();
		driver.quit();

		crawler.crawl(rwd);
		System.out.println("Crawl Complete");
	}
}