package com.appium.mac;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;

public class FirstProject {

	@BeforeMethod()
	public AppiumDriver initDriver() throws MalformedURLException {
		AppiumDriver driver = null;
		try {		
			DesiredCapabilities caps = new DesiredCapabilities();

			caps.setCapability("platformName", "Mac");
			caps.setCapability("deviceName", "Mac");
			//caps.setCapability("app", "Slack");
			caps.setCapability("app", "Google Chrome");
			//caps.setCapability("app", "Activity Monitor");
			caps.setCapability("newCommandTimeout", 300);
			driver = new AppiumDriver(new URL("http://localhost:4723/wd/hub"), caps);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}catch(Throwable t) {
			t.printStackTrace();
		}
		return driver;
	}

	@Test(enabled=false)
	public void testSlack() {
		try {
			AppiumDriver driver = initDriver();			
			driver.findElementByXPath("/AXApplication[@AXTitle='Slack']/AXWindow[@AXTitle='Slack | Ahamed Abdul Rahman | Tekion' and @AXSubrole='AXStandardWindow']/AXScrollArea").click();
			driver.findElementByXPath("/AXApplication[@AXTitle='Slack']/AXWindow[@AXTitle='Slack | Ahamed Abdul Rahman | Tekion' and @AXSubrole='AXStandardWindow']/AXScrollArea").sendKeys("Hi");
			driver.findElementByXPath("/AXApplication[@AXTitle='Slack']/AXWindow[@AXTitle='Slack | Ahamed Abdul Rahman | Tekion' and @AXSubrole='AXStandardWindow']/AXScrollArea").sendKeys(Keys.ENTER);
			
		}catch(Throwable t) {
			t.printStackTrace();
		}
	}
	
	@Test(enabled=true)
	public void testChrome() {
		try {
			AppiumDriver driver = initDriver();			
			//driver.findElementByXPath("/AXApplication[@AXTitle='Chrome']/AXWindow[@AXTitle='Releases · appium/appium-for-mac - Google Chrome' and @AXSubrole='AXStandardWindow']/AXGroup[@AXTitle='Releases · appium/appium-for-mac - Google Chrome']/AXTabGroup[0]/AXButton[@AXTitle='New Tab']").click();
			driver.findElementByXPath("/AXApplication[@AXTitle='Chrome']/AXWindow[@AXTitle='New Tab - Google Chrome' and @AXSubrole='AXStandardWindow']/AXGroup[@AXTitle='New Tab - Google Chrome']/AXGroup[0]/AXTextField[@AXTitle='Address and search bar']").sendKeys("www.google.com");
			driver.findElementByXPath("/AXApplication[@AXTitle='Chrome']/AXWindow[@AXTitle='New Tab - Google Chrome' and @AXSubrole='AXStandardWindow']/AXGroup[@AXTitle='New Tab - Google Chrome']/AXGroup[0]/AXTextField[@AXTitle='Address and search bar']").sendKeys(Keys.ENTER);
		}catch(Throwable t) {
			t.printStackTrace();
		}
	}
	
	@Test(enabled=false)
	public void testActivityMonitor() {
		try {
			AppiumDriver driver = initDriver();	
			String baseAXPath = "/AXApplication[@AXTitle='Activity Monitor']/AXWindow";
			String tabSelectorTemplate = baseAXPath + "/AXToolbar/AXGroup/AXRadioGroup/AXRadioButton[@AXTitle='%s']";
			driver.findElementByXPath(String.format(tabSelectorTemplate, "Memory")).click();
			driver.findElementByXPath(String.format(tabSelectorTemplate, "Energy")).click();
			driver.findElementByXPath(String.format(tabSelectorTemplate, "Disk")).click();
			driver.findElementByXPath(String.format(tabSelectorTemplate, "Network")).click();
			driver.findElementByXPath(String.format(tabSelectorTemplate, "CPU")).click();

			WebElement searchField = driver.findElementByXPath(baseAXPath + "/AXToolbar/AXGroup/AXTextField[@AXSubrole='AXSearchField']");
			searchField.sendKeys("Activity Monitor");

			WebElement firstRow = driver.findElementByXPath(baseAXPath + "/AXScrollArea/AXOutline/AXRow[0]/AXStaticText");			
			Assert.assertEquals(" Activity Monitor", firstRow.getText());
		}catch(Throwable t) {
			t.printStackTrace();
		}
	}
}
