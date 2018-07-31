package coverage.util.scroll.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import coverage.util.ElementScrollIntoViewHelper;

public class BottomOfPageTest {

	@Test
	public void test() throws IOException {
		//site-footer
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.com/");
//		driver.manage().window().maximize();
		Dimension d = new Dimension(1000, 1000);
		driver.manage().window().setSize(d);
		WebElement googleSubmitButton = driver.findElement(By.cssSelector(".navFooterLine"));
		ElementScrollIntoViewHelper esivh = new ElementScrollIntoViewHelper();
		esivh.scroll(driver, googleSubmitButton);
		

		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File("BottomOfPage.jpg"));
		
		driver.close();
		driver.quit();
	}

}
