package graduateprojectpackage;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelpMethodsT {

	public static void insertNamePassword(String email, String password, WebDriver dr) {

		dr.findElement(LocatorsT.login).click();
		dr.findElement(LocatorsT.fieldEmail).clear();
		dr.findElement(LocatorsT.fieldEmail).sendKeys(email);
		dr.findElement(LocatorsT.passsword).clear();
		dr.findElement(LocatorsT.passsword).sendKeys(password);
		dr.findElement(LocatorsT.buttonLogin).click();
	}

	public static void downloadPicture(WebDriver drive, String pictureSource) {
		WebElement web1 = drive.findElement(LocatorsT.buttonDownloadPicture);
		Actions action = new Actions(drive);
		action.moveToElement(web1).click().perform();

		drive.findElement(LocatorsT.buttonDownloadPicture).clear();
		drive.findElement(LocatorsT.buttonDownloadPicture).sendKeys(pictureSource);

		new WebDriverWait(drive, 30).until(ExpectedConditions.visibilityOfElementLocated(LocatorsT.buttonSavePicture));

		drive.findElement(LocatorsT.buttonSavePicture).click();

		new WebDriverWait(drive, 30)
				.until(ExpectedConditions.visibilityOfElementLocated(LocatorsT.buttonCalculatePrice));
		drive.findElement(LocatorsT.buttonCalculatePrice).click();
	}

	public static void assertOrdersTable(int number, WebDriver engine, String salon, String artist, String date) {
		WebElement web1 = engine.findElement(LocatorsT.buttonHome);
		Actions action = new Actions(engine);
		action.moveToElement(web1).click().build().perform();
		// engine.findElement(LocatorsT.buttonHome).click();
		engine.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebElement table = engine.findElement(By.xpath("//div[@class=\"main_page\"]/div"));
		WebTable tableObj = new WebTable(table);
		String ordersTime = tableObj.getCell(tableObj.getRowCount(), 5).getText();
		String ordersSalon = tableObj.getCell(tableObj.getRowCount(), 3).getText();
		String ordersArtist = tableObj.getCell(tableObj.getRowCount(), 4).getText();

		assertEquals(tableObj.getRowCount(), (number + 1));
		assertEquals(ordersSalon, salon);
		assertEquals(ordersArtist, artist);
		assertEquals(ordersTime, date);
		engine.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	public static int countOrders(WebDriver drr) {
		drr.findElement(LocatorsT.buttonHome).click();
		WebElement table = drr.findElement(By.xpath("//div[@class=\"main_page\"]/div"));
		WebTable tableObj = new WebTable(table);
		int size = tableObj.getRowCount();
		return size;
	}
}
