package d15_09_2022;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Zadatak3 {

//	Napisati program koji 
//	Ucitava https://seeds.sproutsocial.com/components/loader-button/
//	Odskrola do Loader buttons are used to display a loading indicator inside of a button. paragrafa. Koristan link
//	Klikce ne dugme 
//	Ceka da dugme zavrsi sa loadingom 
//	Ispisati poruku na ekranu
//	Zatvoriti pretrazivac
//	HINT: Koristite data-qa-button-isloading  atribut elementa za cekanje odredjenog stanja tog elementa

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		driver.get("https://seeds.sproutsocial.com/components/loader-button/");
		
		WebElement hoverable = driver.findElement(By.xpath("//div[text()='Click me to load!']"));
		new Actions(driver).moveToElement(hoverable).perform();
	
		try {
			driver.findElement(By.xpath("//button[@data-qa-button-isloading='false']")).click();
			System.out.println("Klik!");
			driver.findElement(By.xpath("//button[@data-qa-button-isloading='false']"));
			System.out.println("Finish!");
		} catch (Exception e) {
			System.out.println("Greška!");
		}
		
		Thread.sleep(5000);
		driver.quit();
	}

}
