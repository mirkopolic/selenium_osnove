package d19_09_2022;

import java.io.File;
import java.time.Duration;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class BootstrapTableTests {

	private WebDriver driver;
	private WebDriverWait wait;
	private String baseUrl = "https://s.bootsnipp.com";

	@BeforeClass
	public void beforeClass() {
		System.out.println("Before Class");
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		
		

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.manage().window().maximize();

	}

	@BeforeMethod
	public void beforeMethod() {
		driver.get(baseUrl);
	}
	
	@Test(priority = 100)
	public void editRow() {
		
		driver.get(baseUrl+"/iframe/K5yrx");
		
		Assert.assertEquals(
				driver.getTitle(), 
				"Table with Edit and Update Data - Bootsnipp.com",
				"ERROR: Wrong page title.");

		driver.findElement(By.xpath("//button[@data-target='#edit']")).click();
		wait.until(ExpectedConditions.attributeToBe(driver.findElement(By.id("edit")), "aria-hidden", "false"));
		
		driver.findElement(By.id("fn")).clear();
		driver.findElement(By.id("ln")).clear();
		driver.findElement(By.id("mn")).clear();
		
		driver.findElement(By.id("fn")).sendKeys("Mirko");
		driver.findElement(By.id("ln")).sendKeys("Polic");
		driver.findElement(By.id("mn")).sendKeys("Aleksandar");
		
		driver.findElement(By.id("up")).click();
		wait.until(ExpectedConditions.attributeToBe(driver.findElement(By.id("edit")), "aria-hidden", "true"));
				
		Assert.assertEquals(
				driver.findElement(By.id("f1")).getText(),
				"Mirko",
				"ERROR: Wrong name entered");
		
		Assert.assertEquals(
				driver.findElement(By.id("l1")).getText(),
				"Polic",
				"ERROR: Wrong surname entered");
		
		Assert.assertEquals(
				driver.findElement(By.id("m1")).getText(),
				"Aleksandar",
				"ERROR: Wrong middle name entered");
				
	}
	
	@Test(priority = 200)
	public void deleteRow() {
		
		driver.get(baseUrl+"/iframe/K5yrx");
		
		Assert.assertEquals(
				driver.getTitle(), 
				"Table with Edit and Update Data - Bootsnipp.com",
				"ERROR: Wrong page title.");
		
		int brojRedova=driver.findElements(By.xpath("//tbody/tr")).size();
		driver.findElement(By.xpath("//button[@data-target='#delete']")).click();
		
		wait.until(
				ExpectedConditions.attributeToBe(driver.findElement(By.id("delete")),
						"aria-hidden",
						"false"));
						
		driver.findElement(By.id("del")).click();
		wait.until(
				ExpectedConditions.attributeToBe(driver.findElement(By.id("delete")),
				"aria-hidden",
				"true"));
		
		Assert.assertTrue(
				brojRedova != driver.findElements(By.xpath("//tbody/tr")).size() + 1,
				"ERROR: Number of rows is not right!");
		
	}
	
	@Test(priority = 300)
	public void takeAScreenshot() throws Exception {
		
		driver.get(baseUrl+"/iframe/K5yrx");
		
		Assert.assertEquals(
				driver.getTitle(), 
				"Table with Edit and Update Data - Bootsnipp.com",
				"ERROR: Wrong page title.");
		
		String path = "c:\\Users\\Mirko Polic\\eclipse-workspace\\selenium_osnove\\img\\screenshot3.jpg";
		
		BootstrapTableTests.takeSnapShot(driver, path);

//		Screenshot screenShot= new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
//		ImageIO.write(screenShot.getImage(), "jpg", new File(path));

		
		//takeSnapShot(driver, path); 
			
	}
	
//	  public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{
//
//	        TakesScreenshot scrShot =((TakesScreenshot)webdriver);
//	        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
//	        File DestFile=new File(fileWithPath);
//	        FileUtils.copyFile(SrcFile, DestFile);
//
//	    }
	
	public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{

		TakesScreenshot scrShot =((TakesScreenshot)webdriver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(fileWithPath);
        FileUtils.copyFile(SrcFile, DestFile);

    }
	

		
	@AfterMethod
	public void afterMethod() {
		// System.out.println("After Method");

	}

	@AfterClass
	public void afterClass() {
		// System.out.println("After Class");
		driver.quit();

	}
	
}
