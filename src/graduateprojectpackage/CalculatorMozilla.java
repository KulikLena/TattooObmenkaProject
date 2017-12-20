package graduateprojectpackage;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CalculatorMozilla {
	private WebDriver driverMozilla;
	//int number;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "d:\\geckodriver.exe");
		driverMozilla = new FirefoxDriver();
		driverMozilla.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test /*(groups = { "Critical", "Tattoage" })*/
	public void testSizes() throws Exception {
		
			try {
				driverMozilla.get(Parametrs“.baseUrl + "/");
				HelpMethodsT.insertNamePassword(Parametrs“.emmail, Parametrs“.password, driverMozilla);
			} catch (WebDriverException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			driverMozilla.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			int number = HelpMethodsT.countOrders(driverMozilla);
			try {
			WebElement web1 = driverMozilla.findElement(LocatorsT.menuTatouage);
			Actions action = new Actions(driverMozilla);
			action.moveToElement(web1).click().perform();
			// driverMozilla.findElement(LocatorsT.menuTatouage).click();
			driverMozilla.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driverMozilla.findElement(LocatorsT.selectCalculator).click();
			driverMozilla.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HelpMethodsT.downloadPicture(driverMozilla, "d:\\image1.jpg");

		new WebDriverWait(driverMozilla, 30)
				.until(ExpectedConditions.visibilityOfElementLocated(LocatorsT.buttonOrder));

		driverMozilla.findElement(LocatorsT.buttonOrder).click();
		int select = 4;
		new Select(driverMozilla.findElement(LocatorsT.selectArtistInOrder)).selectByIndex(select);
		String artist = driverMozilla
				.findElement(By.xpath("//select[@id=\"artist_select\"]/option[@value=\"" + select + "\"]")).getText();
		String salon = new String(driverMozilla.findElement(By.id("saloon_hide")).getText());
		String date = "21-08 13:50";

		new Select(driverMozilla.findElement(LocatorsT.selectDay)).selectByIndex(21);
		new Select(driverMozilla.findElement(LocatorsT.selectMonth)).selectByIndex(8);
		new Select(driverMozilla.findElement(LocatorsT.selectHour)).selectByIndex(14);
		new Select(driverMozilla.findElement(LocatorsT.selectMinute)).selectByIndex(11);

		WebElement buttonOrder = driverMozilla.findElement(LocatorsT.buttonOrder);
		Actions action = new Actions(driverMozilla);
		action.moveToElement(buttonOrder).click().perform();

		HelpMethodsT.assertOrdersTable(number, driverMozilla, "-", artist, date);

		driverMozilla.findElement(By.linkText("Logout")).click();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driverMozilla.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driverMozilla.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driverMozilla.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driverMozilla.switchTo().alert();
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
