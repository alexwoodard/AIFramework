package clicker;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ClickerTest {

	@Test
	public void test() throws InterruptedException, IOException {
		Clicker clicker = new Clicker();
		WebDriver driver = new ChromeDriver();
		clicker.crawl(driver);
	}

}
