package test;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import  org.junit.Assert;//I added it
import org.junit.Before;
import org.junit.Test;

//import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.regex.Pattern;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;




public class LoginTest {
	
	
private WebDriver driver;

private static String chromePath = "/eBayLite/WebContent/WEB-INF/chromedriver.exe";
private static String systemPath = HomepageTest.class.getProtectionDomain().getCodeSource().getLocation().toExternalForm().replace("file:/", "").replace("eBayLite/build/classes/", "").replace("%20", " ");

    
	@Before
	public void setUp() throws Exception {
	   		
		System.setProperty("webdriver.chrome.driver", systemPath + chromePath);


		 driver=new ChromeDriver(); 
	
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   }
	@Test 
	public void login() throws Exception  { 
		
	
		
		driver.get("http://chriskiihne.ddns.net:8080/eBayLite/Login.html"); 
		
		WebElement email=driver.findElement(By.id("Email")); 
		WebElement password=driver.findElement(By.id("Password")); 
		WebElement login=driver.findElement(By.xpath("//button[text()='Sign in']")); 
		email.sendKeys("test1@gmail.com"); password.sendKeys("test1"); 
		login.click(); String actualUrl="http://chriskiihne.ddns.net:8080/eBayLite/Homepage"; 
		String expectedUrl= driver.getCurrentUrl(); 
		Assert.assertEquals(expectedUrl,actualUrl); 
	}   


}
