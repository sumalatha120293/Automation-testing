package com.test.gmail;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Gmail_Title {
	WebDriver driver;
@BeforeTest
@Parameters(("signupurl"))
public void setup(String signupurl) {
//public void setup() {
	System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
	 driver = new ChromeDriver();
	
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().pageLoadTimeout(1000,TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(1000,TimeUnit.SECONDS);
	driver.get(signupurl);
	
	
}

@Test()
public void title() throws IOException {
	Properties prop = new Properties();
	FileInputStream ip = new FileInputStream("C:\\Users\\om sai ram\\eclipse-workspace\\Gmail\\src\\com\\testdata\\config.properties");
	prop.load(ip);
	WebElement gmail_xpath = driver.findElement(By.xpath(prop.getProperty("gmail_xpath")));
	gmail_xpath.click();
	//WebElement anotheract = driver.findElement(By.xpath(prop.getProperty("anotheract_xpath")));
	//anotheract.click();
	WebElement createact = driver.findElement(By.xpath(prop.getProperty("createact_xpath")));
	createact.click();
	//WebElement myself = driver.findElement(By.xpath("myself_xpath"));
	//myself.click();
	Set<String> handler = driver.getWindowHandles();
	Iterator<String> it =  handler.iterator();
	String parentwindowid = it.next();
	System.out.println("parenet window id:" + parentwindowid);
	String childwindowid = it.next();
	System.out.println("child window id:" + childwindowid);
	
	driver.switchTo().window(parentwindowid);
	driver.close();
	driver.switchTo().window(childwindowid);

	String pagetitle = driver.getTitle();
	Assert.assertEquals(pagetitle, "Create your Google Account", "title not matched");
}
}
