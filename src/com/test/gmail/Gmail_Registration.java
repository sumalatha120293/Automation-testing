package com.test.gmail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.testutility.testutil;
 


public class Gmail_Registration {
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


@DataProvider 
public Iterator<Object[]> gettestdata() {
	ArrayList<Object[]> testdata =  testutil.getdatafromexcel();
	return testdata.iterator();
}

@Test(dataProvider="gettestdata")
public void Registration(String firstname, String lastname, String gmailaddress,String password, String confirmpassword ) throws IOException {
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

	WebElement firstname_input = driver.findElement(By.xpath(prop.getProperty("firstname_xpath")));
	WebElement lastname_input = driver.findElement(By.xpath(prop.getProperty("lastname_xpath")));
	WebElement gmail_input = driver.findElement(By.xpath(prop.getProperty("gmailusername_xpath")));
	WebElement password_input = driver.findElement(By.xpath(prop.getProperty("password_xpath")));
	WebElement confirmpassword_input = driver.findElement(By.xpath(prop.getProperty("confirmpassword_xpath")));
	WebElement next_click = driver.findElement(By.xpath(prop.getProperty("next_xpath")));
	firstname_input.clear();
	firstname_input.sendKeys(firstname);
	lastname_input.clear();
	lastname_input.sendKeys(lastname);
	gmail_input.clear();
	gmail_input.sendKeys(gmailaddress);
	password_input.clear();
	password_input.sendKeys(password);
	confirmpassword_input.clear();
	confirmpassword_input.sendKeys(confirmpassword);
	next_click.click();
}

//@AfterMethod
/*public void teardown() {
	//driver.close();
}*/
}
