package d15_09_2022;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Zadatak1 {

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

		List<WebElement> lista = driver.findElements(By.className("close"));

		int brojac = lista.size();
		WebElement pomocni;
		
		for (int i = 0; i < brojac; i++) {
			pomocni = lista.get(lista.size() - 1);
			lista.get(lista.size() - 1).click();
		
			try {
				pomocni.click();
			} catch (Exception e) {
				System.out.println("Element izbrisan!");
				// lista.remove(lista.size() - 1); 					//Brisemo poslednji element iz postojece liste
				lista = driver.findElements(By.className("close")); // Pravi se nova lista bez kliknutog elementa
				Thread.sleep(1000);
			}
		}

		Thread.sleep(5000);
		driver.quit();
	}

}
