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


//import org.testng.Assert;
//import org.testng.annotations.Test;

//import static org.junit.Assert.*;


public class LoginTest {
	
	
private WebDriver driver;
private StringBuffer verificationErrors = new StringBuffer();

    
	@Before
	public void setUp() throws Exception {
	    ///  System.setProperty("webdriver.chrome.driver", "lib//win//chromedriver.exe");
	    //  driver = new ChromeDriver();
	    //  baseUrl = "https://www.katalon.com/";
	    //  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
		System.setProperty("webdriver.chrome.driver", "lib//win//chromedriver.exe"); 
		 driver=new ChromeDriver(); 
	//	driver.manage().window().maximize(); 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   }
	@Test 
	public void login() throws Exception  { 
		
		//System.setProperty("webdriver.chrome.driver", "lib//win//chromedriver.exe"); 
		//WebDriver driver=new ChromeDriver(); driver.manage().window().maximize(); 
		
		driver.get("http://chriskiihne.ddns.net:8080/eBayLite/Login.html"); 
		
		WebElement email=driver.findElement(By.id("Email")); 
		WebElement password=driver.findElement(By.id("Password")); 
		WebElement login=driver.findElement(By.xpath("//button[text()='Sign in']")); 
		email.sendKeys("test1@gmail.com"); password.sendKeys("test1"); 
		login.click(); String actualUrl="http://chriskiihne.ddns.net:8080/eBayLite/Homepage"; 
		String expectedUrl= driver.getCurrentUrl(); 
		Assert.assertEquals(expectedUrl,actualUrl); 
	}   
	@After
	   public void tearDown() throws Exception {
	      driver.quit();
	     String verificationErrorString = verificationErrors.toString();
	     if (!"".equals(verificationErrorString)) {
	        fail(verificationErrorString);
	     }
	   }

}
