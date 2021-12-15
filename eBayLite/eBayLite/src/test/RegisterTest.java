package test;


import org.junit.After;
import  org.junit.Assert;//I added it
import org.junit.Before;
import org.junit.Test;

//import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

//import org.testng.Assert;
//import org.testng.annotations.Test;

//import static org.junit.Assert.*;

public class RegisterTest {
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
    public void registerTest() throws Exception {    
	
    	//validRegistrationTest
	//	System.setProperty("webdriver.chrome.driver", "lib//win//chromedriver.exe"); 
	//	WebDriver driver=new ChromeDriver(); 
	//	driver.manage().window().maximize(); 
	 
		driver.get("http://chriskiihne.ddns.net:8080/eBayLite/Register.html"); 
              
          
             
        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys("test1@gmail.com");
        
        WebElement confirmEmail = driver.findElement(By.name("email"));
        confirmEmail.sendKeys("test1@gmail.com");
        
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("test1");
        
        WebElement confirmPassword = driver.findElement(By.name("confirmpassword"));
        confirmPassword.sendKeys("test1");
              
        WebElement signUp = driver.findElement(By.xpath("//button[contains(@class,'btn sign-up-btn-2 btn-block')]"));
        signUp.click();
        
        String expectedURL = "http://chriskiihne.ddns.net:8080/eBayLite/Homepage";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL);
       
                
    }
    
	//@After
	  // public void tearDown() throws Exception {
	    //  driver.quit();
	     //String verificationErrorString = verificationErrors.toString();
	     //if (!"".equals(verificationErrorString)) {
	       // fail(verificationErrorString);
	     //}
	   //}


///////////////////////////////////////////



}
