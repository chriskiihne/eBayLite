package test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AuctionTest {
	private static WebDriver driver;
	private static String chromePath = "/eBayLite/WebContent/WEB-INF/chromedriver.exe";
	private static String systemPath = AuctionTest.class.getProtectionDomain().getCodeSource().getLocation().toExternalForm().replace("file:/", "").replace("eBayLite/build/classes/", "").replace("%20", " ");
	
	
	@BeforeClass
	public static void setup() {
		System.setProperty("webdriver.chrome.driver", systemPath + chromePath);
		driver = new ChromeDriver();
		
	}
	
	@Before
	public void navigate() {
		driver.get("http://chriskiihne.ddns.net:8080/eBayLite/Auctioning.html");
	}
	
	
	@Test
	public void testScreen()
	{
		Assert.assertEquals("Incorrect Auction Setup Title", "eBay Lite Auction Setup", driver.findElement(By.xpath("//div/h1")).getText());
		
	}
	
	public void login() {
		driver.findElement(By.linkText("eBay Lite")).click();
		Assert.assertEquals("Incorrect Homepage Title", "Ebay Lite Home", driver.findElement(By.xpath("//header/h1")).getText());
		driver.findElement(By.linkText("Login")).click();
		Assert.assertEquals("Incorrect Login Title", "Login", driver.findElement(By.xpath("//header/h1")).getText());
		
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("test@email"); 
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("test"); 
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		Assert.assertEquals("Login Failed", "Login Successful.", driver.findElement(By.xpath("//body/h3")).getText());
		
		driver.findElement(By.linkText("Home")).click();
		Assert.assertEquals("Incorrect Homepage Title", "Ebay Lite Home", driver.findElement(By.xpath("//header/h1")).getText());
		driver.findElement(By.linkText("Auctioning")).click();
		testScreen();
	}
	
	@Test
	public void testAdd()
	{
		login();
		
		String itemName = "SeleniumItem" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
		driver.findElement(By.id("ItemName")).sendKeys( itemName );
		driver.findElement(By.id("Description")).sendKeys("SeleniumDescription" );
		driver.findElement(By.id("StartingBid")).sendKeys("10");
		driver.findElement(By.id("AuctionLength")).sendKeys("1");
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		
		driver.get("http://chriskiihne.ddns.net:8080/eBayLite/Homepage");
		
		WebElement element = driver.findElement(By.xpath(String.format("//section/a[contains(@href, 'ItemView?item')][contains(text(), '%s')]", itemName)));
		String itemListing = itemName + " " + LocalDate.now().toString() + " " + LocalDate.now().plusDays(1).toString();
		Assert.assertEquals("Added Item does not Exist", itemListing, element.getText());
	}
	
	@Test
	public void testLinks()
	{
		driver.findElement(By.linkText("eBay Lite")).click();
		Assert.assertEquals("Incorrect Homepage Title", "Ebay Lite Home", driver.findElement(By.xpath("//header/h1")).getText());
	}
	
	@AfterClass
	 public static void closeBrowser() {
		driver.close();
	 }
}
