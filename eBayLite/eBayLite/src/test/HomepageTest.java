package test;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HomepageTest {
	private static WebDriver driver;
	private static String chromePath = "/eBayLite/WebContent/WEB-INF/chromedriver.exe";
	private static String systemPath = HomepageTest.class.getProtectionDomain().getCodeSource().getLocation().toExternalForm().replace("file:/", "").replace("eBayLite/build/classes/", "").replace("%20", " ");
	
	
	@BeforeClass
	public static void setup() {
		System.setProperty("webdriver.chrome.driver", systemPath + chromePath);
		driver = new ChromeDriver();
	}
	
	@Before
	public void navigate()
	{
		driver.get("http://chriskiihne.ddns.net:8080/eBayLite/Homepage");
	}
	
	@Test
	public void testScreen()
	{
		Assert.assertEquals("Incorrect Homepage Title", "Ebay Lite Home", driver.findElement(By.xpath("//header/h1")).getText());
	}
	
	@Test
	public void testLinks()
	{
		driver.findElement(By.linkText("Login")).click();
		Assert.assertEquals("Incorrect Login Title", "Login", driver.findElement(By.xpath("//header/h1")).getText());
		driver.findElement(By.linkText("Home")).click();
		testScreen();
		
		driver.findElement(By.linkText("Auctioning")).click();
		Assert.assertEquals("Incorrect Auction Setup Title", "eBay Lite Auction Setup", driver.findElement(By.xpath("//div/h1")).getText());
	}
	
	@Test
	public void testItems()
	{
		List<WebElement> elements = driver.findElements(By.xpath("//section/a[contains(@href, 'ItemView.html?item')]"));
		Assert.assertTrue("No Items on Homepage", elements.size() > 0);
		
		elements.get(0).click();
		Assert.assertEquals("Item Page did not open", "Item View Page", driver.findElement(By.xpath("//section/p")).getText());
	}
	

	
	

	@AfterClass
	 public static void closeBrowser() {
		// TOOD
		driver.close();
	 }
}
