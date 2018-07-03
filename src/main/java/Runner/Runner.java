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
import org.openqa.selenium.remote.RemoteWebDriver;

import clickablefinder.ClickerCrawler;

public class Runner {

	public static void main(String args[]) throws InterruptedException, IOException {

		String gridAddress = "http://10.32.64.63:4444/wd/hub";
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		ChromeDriver rwd =new ChromeDriver();
//		RemoteWebDriver rwd = new RemoteWebDriver(new URL(gridAddress), capability);
		ClickerCrawler crawler = new ClickerCrawler();
		rwd.get("https://www.godaddy.com/websites/website-builder");
		rwd.manage().window().maximize();
		Dimension d = new Dimension(2024, 900);
		rwd.manage().window().setSize(d);
		synchronized (rwd) {
			rwd.wait(5000);
			File scrFile = ((TakesScreenshot)rwd).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("screenshot.png"));
		}

		crawler.search(rwd);
		System.out.println("Crawl Complete");
	}
}