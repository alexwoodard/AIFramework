package Runner;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import clickablefinder.BfsAlgorithm;
import clickablefinder.PageComponent;
import clickablefinder.XpathSelectorBuilder;

public class Runner {

	public static void main(String args[]) throws InterruptedException, IOException {

		XpathSelectorBuilder xpathSelectorBuilder = new XpathSelectorBuilder();
		String gridAddress = "http://10.32.64.63:4444/wd/hub";
		DesiredCapabilities capability = DesiredCapabilities.chrome();
		RemoteWebDriver rwd = new RemoteWebDriver(new URL(gridAddress), capability);
		BfsAlgorithm bfs_algorithm = new BfsAlgorithm();
		rwd.get("https://www.godaddy.com");
		rwd.manage().window().maximize();
		synchronized (rwd) {
			rwd.wait(5000);
			File scrFile = ((TakesScreenshot)rwd).getScreenshotAs(OutputType.FILE);
			// Now you can do whatever you need to do with it, for example copy somewhere
			FileUtils.copyFile(scrFile, new File("screenshot.png"));
		}

		WebElement rootElement = rwd.findElement(By.cssSelector("body"));
		String rootXpathSelector = xpathSelectorBuilder.generateXPATH(rootElement, "");
		PageComponent pc = new PageComponent(rootXpathSelector, null, null);
		String result = bfs_algorithm.search(pc, rwd);
		System.out.println("Crawl Complete");
	}
}