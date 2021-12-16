package test;

import  org.junit.Assert;//I added it
import org.junit.Before;
import org.junit.Test;

//import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;




public class LoginTest {
	
	
private WebDriver driver;

private static String chromePath = "/eBayLite/WebContent/WEB-INF/chromedriver.exe";
private static String systemPath = HomepageTest.class.getProtectionDomain().getCodeSource().getLocation().toExternalForm().replace("file:/", "").replace("eBayLite/build/classes/", "").replace("%20", " ");

    
	@Before
	public void setUp() throws Exception {
	   		
		System.setProperty("webdriver.chrome.driver", systemPath + chromePath);


		 driver=new ChromeDriver(); 
	
	   }
	@Test 
	public void login() throws Exception  { 
		
	
		
		driver.get("http://chriskiihne.ddns.net:8080/eBayLite/Login.html"); 
		
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("test@email"); 
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("test"); 
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		String actualUrl="http://chriskiihne.ddns.net:8080/eBayLite/Login"; 
		String expectedUrl= driver.getCurrentUrl(); 
		Assert.assertEquals(expectedUrl,actualUrl); 
	}   


}
