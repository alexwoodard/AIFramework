package Runner;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import chrome.ChromeDriverEx;
import clicker.Clicker;
import coverage.fullpage.FullPageScreenshotBuilder;

public class Runner {

	public static void main(String args[]) throws Exception {
		
		String pageUnderTest = args[0];
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		ChromeDriver rwd =new ChromeDriver();
		Clicker crawler = new Clicker();
		rwd.get(pageUnderTest);
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
		rwd.close();
		rwd.quit();
		System.out.println("Crawl Complete");
	}
}