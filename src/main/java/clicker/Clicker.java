package clicker;

import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import component.clickable.Clickable;
import coverage.ClickCoverage;
import coverage.CoverageAnalyzer;
import crawler.ClickerCrawler;
import ignore.IgnoreResolver;
import learning.NearestNeighbor;
import util.FileParser;

public class Clicker {
	ClickerCrawler clickerCrawler = new ClickerCrawler();

	public void crawl(WebDriver driver) throws InterruptedException, IOException {
		String urlUnderTest = driver.getCurrentUrl();
		/**
		 * get all selectors of components where you want to ignore clickables
		 */
		FileParser fp = new FileParser();
		ArrayList<String> clickablesToIgnore = fp.parseLines("ignore");

		/**
		 * 
		 * first figure out which clickables are on the page under test.
		 */
		ArrayList<Clickable> clickablesUnderTest = new ArrayList<Clickable>();
		// ArrayList<WebElement> clickableElementsToIgnore =
		// (ArrayList<WebElement>) clickerCrawler
		// .getAllIgnorableClickableElements(driver, clickablesToIgnore);
		IgnoreResolver ir = new IgnoreResolver(driver, clickablesToIgnore);
		for (WebElement element : clickerCrawler.getAllClickableElements(driver)) {
			if (clickerCrawler.elementIsVisible(element)) {
				Boolean ignore = false;
				// for (WebElement ignorableElement : clickableElementsToIgnore)
				// {
				if (!ir.ignoreComponent(element)) {
					try {
						/**
						 * Random file name
						 */
						Random rand = new Random();
						int n = rand.nextInt(50) + 1;
						String fileName = String.valueOf(n)+".jpg";
						clickablesUnderTest.add(new Clickable(element,true,String.valueOf(n),driver));
					} catch (AWTException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					System.out.println("ignoring a component");
				}
				// }
				// if (!ignore)
			}
		}

		/**
		 * do a nearest neighbor since selectors change if things start to not
		 * show up like sub nav bar.
		 */
		NearestNeighbor nearestNeighbor = new NearestNeighbor();
		ArrayList<Clickable> clickablesClicked = new ArrayList<Clickable>();
		ClickCoverage clickCoverage = new ClickCoverage();
		for (Clickable p : clickablesUnderTest) {
			int nearestNeighborScore = Integer.MAX_VALUE;
			WebElement nearestNeighborElement = null;
			Boolean clicked = false;
			synchronized (driver) {
				driver.get(urlUnderTest);
				driver.wait(3000);
				/**
				 * just to make sure that the previous CTA did not cause a
				 * scroll position on reload
				 */
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("window.scrollBy(0,-9999999)", "");
			}
			for (WebElement element : clickerCrawler.getAllClickableElements(driver)) {
				Clickable clickable = null;
				try {
					clickable = new Clickable(element, false, null, null);
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				int neighborScore = nearestNeighbor.computeScore(p, clickable);
				if (neighborScore < nearestNeighborScore) {
					nearestNeighborScore = neighborScore;
					nearestNeighborElement = element;
				}
			}
			if (nearestNeighborElement != null) {
				try {
					nearestNeighborElement.click();
				} catch (Exception e) {
					File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(scrFile, new File("failedclick.png"));
					throw e;
				}
				clicked = true;
			}
			if (!clicked) {
				System.out.println("[DEBUG] Could not click on " + nearestNeighborElement.getLocation().x + " "
						+ nearestNeighborElement.getLocation().y);
			} else {
				int bottom = p.getY() + p.getDimension().height;
				System.out.println("[DEBUG] Clicked on Y:" + p.getY() + " down to Y:" + bottom);
				clickCoverage.addClick(p);
			}
			clicked = false;
		}
		CoverageAnalyzer coverageAnalyzer = new CoverageAnalyzer();
		coverageAnalyzer.generateReport(clickCoverage);
	}
}