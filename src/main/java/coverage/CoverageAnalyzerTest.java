package coverage;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import component.clickable.Clickable;

public class CoverageAnalyzerTest {
	@Test
	public void test() throws AWTException, IOException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com");
		driver.manage().window().maximize();
		WebElement googleSubmitButton = driver.findElement(By.name("btnK"));
		Clickable clickable = new Clickable(googleSubmitButton, true, "googlesearchbutton.jpg", driver);
		ClickCoverage cc = new ClickCoverage();
		cc.addClick(clickable);
		CoverageAnalyzer ca = new CoverageAnalyzer();
		try {
			ca.generateReport(cc);
		} catch (Exception e) {
			driver.close();
			driver.quit();
			e.printStackTrace();
		}
		driver.close();
		driver.quit();
	}
}