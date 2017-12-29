package graduateprojectpackage;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WebTable {

	private WebElement _webTable;

	public WebTable(WebElement webTable) {
		set_webTable(webTable);
	}

	public WebElement get_webTable() {
		return _webTable;
	}

	public void set_webTable(WebElement _webTable) {
		this._webTable = _webTable;
	}

	int getRowCount() {
		List<WebElement> tableRows = _webTable.findElements(By.xpath("//div[@class=\"main_page\"]/div/div"));
		return tableRows.size();
	}

	public int getColumnCount() {
		List<WebElement> tableRows = _webTable.findElements(By.xpath("//div[@class=\"main_page\"]/div/div"));
		WebElement headerRow = tableRows.get(0);
		List<WebElement> tableCols = headerRow.findElements(By.className("col-md-2"));
		return tableCols.size();
	}

	public WebElement getCell(int rowIdx, int colIdx) {
		try {
			List<WebElement> tableRows = _webTable.findElements(By.xpath("//div[@class=\"main_page\"]/div/div"));
			WebElement currentRow = tableRows.get(rowIdx - 1);
			List<WebElement> tableCols = currentRow.findElements(By.className("col-md-2"));
			WebElement cell = tableCols.get(colIdx - 1);
			return cell;
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Failed to get cell");
		}
	}
}
