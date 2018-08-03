package coverage.fullpage;

import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeOptions;

import chrome.ChromeDriverEx;

public class FullPageBuilderTest {

	@Test
	public void test() throws Exception {
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", false);
		options.addArguments("disable-infobars");
		ChromeDriverEx driver = new ChromeDriverEx(options);
		driver.get("https://www.godaddy.com");
		driver.manage().window().setSize(new Dimension(1000,1000));
		synchronized(driver){
			driver.wait(3000);
		}
		FullPageScreenshotBuilder fpsb = new FullPageScreenshotBuilder();
		fpsb.build(driver, "images/fullpage/fullpage.png",1000);
		driver.close();
		driver.quit();
	}
}