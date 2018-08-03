package component.clickable;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import coverage.ClickCoverage;
import coverage.analyzer.CoverageAnalyzer;
import coverage.util.ElementScrollIntoViewHelper;

public class ClickableTest {
	@Test
	public void test() throws AWTException, IOException, InterruptedException {
	}
}