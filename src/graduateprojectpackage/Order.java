package graduateprojectpackage;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

import org.openqa.selenium.support.ui.Select;

public class Order {

	private WebDriver driverOpera;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		OperaOptions operaOptions = new OperaOptions();
		operaOptions.setBinary("c:\\Program Files\\Opera\\49.0.2725.39\\opera.exe");
		System.setProperty("webdriver.opera.driver", "d:\\operadriver.exe");
		driverOpera = new OperaDriver(operaOptions);
		driverOpera.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test(groups = { "Critical", "Order"}, dependsOnGroups = {"Map"} )
	public void testSizes() throws Exception {
		driverOpera.get(ParametrsТ.baseUrl + "/");
		HelpMethodsT.insertNamePassword(ParametrsТ.emmail, ParametrsТ.password, driverOpera);
		int numberOfOrders = HelpMethodsT.countOrders(driverOpera);
		driverOpera.findElement(LocatorsT.buttonMainOrder).click();
		int select = 8;
		new Select(driverOpera.findElement(LocatorsT.selectArtistInOrder)).selectByIndex(select);
		String artist = driverOpera
				.findElement(By.xpath("//select[@id=\"artist_select\"]/option[@value=\"" + select + "\"]")).getText();
		String date = "21-08 13:50";
		
		// driverOpera.findElement(By.id("saloon_hide")).click();
		String salon = new String(driverOpera.findElement(LocatorsT.salonHide).getText());
		new Select(driverOpera.findElement(LocatorsT.selectDay)).selectByIndex(21);
		new Select(driverOpera.findElement(LocatorsT.selectMonth)).selectByIndex(8);
		new Select(driverOpera.findElement(LocatorsT.selectHour)).selectByIndex(14);
		new Select(driverOpera.findElement(LocatorsT.selectMinute)).selectByIndex(11);

		WebElement buttonOrder = driverOpera.findElement(LocatorsT.buttonOrder);
		Actions action = new Actions(driverOpera);
		action.moveToElement(buttonOrder).click().perform();
		// салон не ассертится
		HelpMethodsT.assertOrdersTable(numberOfOrders, driverOpera, "-", artist, date);

		driverOpera.findElement(By.linkText("Logout")).click();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driverOpera.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driverOpera.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driverOpera.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driverOpera.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}