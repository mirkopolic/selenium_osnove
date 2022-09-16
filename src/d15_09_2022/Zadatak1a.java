package d15_09_2022;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak1a {

//	Napisati program koji:
//		Ucitava stranicu https://s.bootsnipp.com/iframe/Dq2X
//		Klikce na svaki iks da ugasi obavestenje i proverava da li se nakon klika element obrisao sa stranice i ispisuje odgovarajuce poruke (OVO JE POTREBNO RESITI PETLJOM)
//		POMOC: Brisite elemente odozdo.
//		(ZA VEZBANJE)Probajte da resite da se elemementi brisu i odozgo

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		driver.get("https://s.bootsnipp.com/iframe/Dq2X");
		Thread.sleep(2000);

		List<WebElement> lista = driver.findElements(By.className("close"));

		int brojac = lista.size();
		WebElement pomocni;
		
		//BRIŠE UVEK PRVI
		
		for (int i = 0; i < brojac; i++) {

			pomocni = lista.get(0);
			lista.get(0).click();
			System.out.println("Element izbrisan!");
			lista = driver.findElements(By.className("close"));

			try {
				pomocni.click();
			} catch (Exception e) {
				System.out.println("Element ne postoji!");
				Thread.sleep(1000);
			}
		}

		Thread.sleep(5000);
		driver.quit();
	}

}
