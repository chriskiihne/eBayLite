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
		List<WebElement> elements = driver.findElements(By.xpath("//section/a[contains(@href, 'ItemView?item')]"));
		Assert.assertTrue("No Items on Homepage", elements.size() > 0);
		
		WebElement element = elements.get(0);
		String[] info = element.getText().split(" ");
		String name = info[0];
		String start = info[1];
		String end = info[2];
		String id = element.getAttribute("href").split("=")[1];
		
		element.click();
		Assert.assertEquals("Item ID on Item view is Incorrect", id, driver.findElement(By.xpath("//p[contains(text(), 'ID')]")).getText().split(": ")[1]);
		Assert.assertEquals("Item Name on Item view is Incorrect", name, driver.findElement(By.xpath("//p[contains(text(), 'Name')]")).getText().split(": ")[1]);
		Assert.assertEquals("Item Auction Start on Item view is Incorrect", start, driver.findElement(By.xpath("//p[contains(text(), 'auctionStart')]")).getText().split(": ")[1]);
		Assert.assertEquals("Item Auction End on Item view is Incorrect", end, driver.findElement(By.xpath("//p[contains(text(), 'auctionEnd')]")).getText().split(": ")[1]);
	}
	

	
	

	@AfterClass
	 public static void closeBrowser() {
		driver.close();
	 }
}
