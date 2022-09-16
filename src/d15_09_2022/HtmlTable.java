package d15_09_2022;

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

}
