package graduateprojectpackage;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Services {
	private WebDriver driverOpera;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		OperaOptions operaOptions = new OperaOptions();
		operaOptions.setBinary("c:\\Program Files\\Opera\\49.0.2725.64\\opera.exe");
		System.setProperty("webdriver.opera.driver", "d:\\operadriver.exe");
		driverOpera = new OperaDriver(operaOptions);
		driverOpera.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test(groups = { "Critical", "Order Via Tab" }, dependsOnGroups = { "Tattoage" })
	public void testSizes() throws Exception {
		driverOpera.get(ParametrsÒ.baseUrl + "/");
		HelpMethodsT.insertNamePassword(ParametrsÒ.emmail, ParametrsÒ.password, driverOpera);
		int numberOfOrders = HelpMethodsT.countOrders(driverOpera);
		driverOpera.get(ParametrsÒ.baseUrl + "/");
		driverOpera.findElement(LocatorsT.services).click();
		new Select(driverOpera.findElement(LocatorsT.selectServiceInOrder)).selectByIndex(1);
		new Select(driverOpera.findElement(LocatorsT.selectSaloonInOrder)).selectByIndex(2);

		driverOpera.findElement(LocatorsT.buttonPriceInServices).click();

		HelpMethodsT.downloadPicture(driverOpera, ParametrsÒ.ownpicture);

		new WebDriverWait(driverOpera, 30).until(ExpectedConditions.visibilityOfElementLocated(LocatorsT.buttonOrder));

		driverOpera.findElement(LocatorsT.buttonOrder).click();
		OrderPage pageObj = new OrderPage(driverOpera);
		String artist = pageObj.selectArtist(4);
		String salon = pageObj.salonHide();
		String date = pageObj.selectDate2(27, 7, 12, 10);
		pageObj.orderOrder();

		// ñàëîí íå àññåðòèòñÿ
		HelpMethodsT.assertOrdersTable(numberOfOrders, driverOpera, "-", artist, date);

		driverOpera.findElement(LocatorsT.buttonLogOut).click();
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
