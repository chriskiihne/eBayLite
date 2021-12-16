package test;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.AfterClass;
import  org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class RegisterTest {
	private static WebDriver driver;
	private static String chromePath = "/eBayLite/WebContent/WEB-INF/chromedriver.exe";
	private static String systemPath = HomepageTest.class.getProtectionDomain().getCodeSource().getLocation().toExternalForm().replace("file:/", "").replace("eBayLite/build/classes/", "").replace("%20", " ");
	
    
	@BeforeClass
	public static void setUp(){
	  
		System.setProperty("webdriver.chrome.driver", systemPath + chromePath);
	 
		 driver=new ChromeDriver(); 
	
	   }

    @Test
    public void registerTest() throws Exception {    
	
     
	 
		driver.get("http://chriskiihne.ddns.net:8080/eBayLite/Register.html"); 
              
        String testDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String newEmail = "test" + testDateTime + "@email";
        String newPassword = "test" + testDateTime;
             
        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys(newEmail);
        
        WebElement confirmEmail = driver.findElement(By.name("reEmail"));
        confirmEmail.sendKeys(newEmail);
        
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys(newPassword);
        
        WebElement confirmPassword = driver.findElement(By.name("rePassword"));
        confirmPassword.sendKeys(newPassword);
              
        WebElement signUp = driver.findElement(By.xpath("//input[@value='Register']"));
        signUp.click();
        
        Assert.assertEquals("Incorrect Login Title", "Login", driver.findElement(By.xpath("//header/h1")).getText());
		
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(newEmail); 
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(newPassword); 
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		Assert.assertEquals("Login Failed", "Login Successful.", driver.findElement(By.xpath("//body/h3")).getText());
       
                
    }
    
    @AfterClass
	 public static void closeBrowser() {
		driver.close();
	 }
}
