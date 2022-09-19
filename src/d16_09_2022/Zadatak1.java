package d16_09_2022;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Zadatak1 {

	public static void main(String[] args) throws InterruptedException {
//		Napisati program koji ima:
//		Podesava:
//		implicitno cekanje za trazenje elemenata od 10s
//		implicitno cekanje za ucitavanje stranice od 10s
//		eksplicitno cekanje podeseno na 10s
//		Podaci:
//		Potrebno je u projektu ukljuciti 4 slike.
//		Imenovanje slika neka bude po pravilu pozicija_ime_prezime_
//		polaznika.ekstenzija
//		Npr: front_milan_jovanovic.jpg, left_milan_jovanovic.jpg â€¦
//		Koraci:
//		Ucitava stranicu https://boomf.com/apps/proxy/boomf-bomb/i-bloody-love-you
//		Maksimizuje prozor
//		Selektuje Image - Front klikom na tu karticu u dnu ekrana
//		Klik na add photo iz levog navigacionog menia
//		Upload slike. Upload preko File objekta koristeci getAbsolutePath
//		Sacekati da broj prikazanih slika u donjem uglu navigacije 
//		bude za 1 veca
//		Kliknuti na poslednje dodatu sliku kako bi bila izabrana za
//		postavljanje
//		Ceka da dijalog bude vidljiv
//		Klik na Use One Side Only dugme
//		Ceka da se pojavi dijalog sa slikom
//		Klik na Done
//		Ponoviti proces za Left, Right i Back
//		Zatim iz desnog gornjeg ugla izabrati random jedan od ponudjenih konfeta
//		Kliknuti na Add To Cart dugme
//		Verifikovati postojanje greske Oops! It looks like you`ve not added an 
//		text to this field, please add one before continuing.
//		Xpath: //*[@action='error']
//		Zatvorite pretrazivac

System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
WebDriver driver = new ChromeDriver();
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
driver.manage().window().maximize();

//Kreira listu sa slikama
List<File> slike = new ArrayList<File>();
slike.add(new File("img/front_slika.jpg"));
slike.add(new File("img/left_slika.jpg"));
slike.add(new File("img/right_slika.jpg"));
slike.add(new File("img/back_slika.jpg"));

//Otvoti stranicu
driver.get("https://boomf.com/apps/proxy/boomf-bomb/i-bloody-love-you");

//Petlja u kojoj se vrši sledece:
//Klikne u (imgNumber) polje 
//Saceka da se otvoti novi prozor za dodavanje slika
//Ucita sliku iz liste i klikne na ucitanu sliku
//Saceka novi prozor i klikne na polje (Use One Side Only)
//Saceka novi prozor i klikne na polje (Done)

for (int i = 0; i < 4; i++) {
	int imgNumber = i + 1;
	driver.findElement(By.xpath("//*[@alt='image " + imgNumber + "']")).click();
	if (i == 0) {
		driver.findElement(By.xpath("//*[@alt='Front']")).click();
	} else {
		driver.findElement(By.xpath("//*[text()='+ Add Image']")).click();
	}
	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("imageUpload")));
	driver.findElement(By.id("imageUpload")).sendKeys(slike.get(i).getAbsolutePath());
	driver.findElement(By.xpath("//*[@loading='lazy'][last()]")).click();
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Use One Side Only']")));
	driver.findElement(By.xpath("//*[text()='Use One Side Only']")).click();
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Done']")));
	driver.findElement(By.xpath("//*[text()='Done']")).click();
}

//Dodaje random Confetti efekat
Random random = new Random();
int x = random.nextInt(5);
driver.findElement(By.xpath("//div[@name='" + x + "']")).click();

//Klikne na dugme (Add to cart)
driver.findElement(By.xpath("//button[@type='submit']")).click();

//Proverava da li postoji tekst greske
boolean elementPostoji = true;
try {
	driver.findElement(By.xpath("//*[@action='error']"));
} catch (Exception e) {
	elementPostoji = false;
}
if (elementPostoji) {
	System.out.println("Postoji tekst greske!");
} else {
	System.out.println("Ne postoji tekst greske!");
}

Thread.sleep(5000);
driver.quit();

	}

}
