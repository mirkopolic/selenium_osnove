package d15_09_2022;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;

public class HtmlTable {

	private WebElement tabela;

	public HtmlTable(WebElement tabela) {
		super();
		this.tabela = tabela;
	}

	public String getCellText(int row, int cell) {

		return this.tabela.findElement(By.xpath("//tbody/tr[" + row + "]/td[" + cell + "  ]")).getText();

	}

	//public List<WebElement> getColumnsByName(String kolona) {
	public void getColumnsByName(String kolona) {
		ArrayList<String> listaIndexa = new ArrayList<String>();
		for (int j = 0; j < 4; j++) {
			
		
		listaIndexa.add(this.tabela.findElement(By.xpath("//thead/tr/th["+j+"]")).getText());
		}
	System.out.println(listaIndexa);	
		
//		return lista;
	}
}
