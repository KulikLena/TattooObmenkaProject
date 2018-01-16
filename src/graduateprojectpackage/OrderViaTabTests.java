package graduateprojectpackage;

import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OrderViaTabTests {
	private WebDriver driverOpera;
	private StringBuffer verificationErrors = new StringBuffer();
	private OrderPage pageObj;

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		OperaOptions operaOptions = new OperaOptions();
		operaOptions.setBinary("c:\\Program Files\\Opera\\49.0.2725.64\\opera.exe");
		System.setProperty("webdriver.opera.driver", "d:\\operadriver.exe");
		driverOpera = new OperaDriver(operaOptions);
		driverOpera.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		pageObj = new OrderPage(driverOpera);
	}

	
	@Test (groups = { "Critical", "Order Via Tab"})
	public void testArtistInOrder() throws Exception {
		driverOpera.get(Parametrs“.baseUrl + "/");
		HelpMethodsT.insertNamePassword(Parametrs“.emmail, Parametrs“.password, driverOpera);
		int numberOfOrders = HelpMethodsT.countOrders(driverOpera);
		driverOpera.get(Parametrs“.baseUrl + "/");
		driverOpera.findElement(LocatorsT.calculatorInOrder).click();
		driverOpera.findElement(LocatorsT.artistsInOrder).click();

		new Select(driverOpera.findElement(LocatorsT.artist3)).selectByIndex(3);

		driverOpera.findElement(LocatorsT.calculate2).click();

		HelpMethodsT.downloadPicture(driverOpera, "d:\\image2.jpg");

		new WebDriverWait(driverOpera, 30).until(ExpectedConditions.visibilityOfElementLocated(LocatorsT.buttonOrder));

		driverOpera.findElement(LocatorsT.buttonOrder).click();

		String artist = pageObj.selectArtist(5);
		String salon = pageObj.salonHide();
		String date = pageObj.selectDate2(10, 3, 10, 10);
		pageObj.orderOrder();

		HelpMethodsT.assertOrdersTable(numberOfOrders, driverOpera, salon, artist, date);

		driverOpera.findElement(LocatorsT.buttonLogOut).click();
	}

	@Test  (groups = { "Critical", "Order Via Tab"})
	public void testSalonInOrder() throws Exception {
		driverOpera.get(Parametrs“.baseUrl + "/");
		HelpMethodsT.insertNamePassword(Parametrs“.emmail, Parametrs“.password, driverOpera);
		int numberOfOrders = HelpMethodsT.countOrders(driverOpera);
		driverOpera.get(Parametrs“.baseUrl + "/");
		driverOpera.findElement(LocatorsT.calculatorInOrder).click();
		driverOpera.findElement(LocatorsT.salonsInOrder).click();

		new Select(driverOpera.findElement(LocatorsT.salons3)).selectByIndex(3);

		driverOpera.findElement(LocatorsT.calculate).click();

		HelpMethodsT.downloadPicture(driverOpera, Parametrs“.ownpicture2);

		new WebDriverWait(driverOpera, 30).until(ExpectedConditions.visibilityOfElementLocated(LocatorsT.buttonOrder));

		driverOpera.findElement(LocatorsT.buttonOrder).click();

		String artist = pageObj.selectArtist(2);
		String salon = pageObj.salonHide();
		String date = pageObj.selectDate2(15, 5, 12, 10);
		pageObj.orderOrder();

		HelpMethodsT.assertOrdersTable(numberOfOrders, driverOpera, salon, artist, date);

		driverOpera.findElement(LocatorsT.buttonLogOut).click();
	}

	@Test  (groups = { "Critical", "Order Via Tab"})
	public void testServices() throws Exception {
		driverOpera.get(Parametrs“.baseUrl + "/");
		HelpMethodsT.insertNamePassword(Parametrs“.emmail, Parametrs“.password, driverOpera);
		int numberOfOrders = HelpMethodsT.countOrders(driverOpera);
		driverOpera.get(Parametrs“.baseUrl + "/");
		driverOpera.findElement(LocatorsT.services).click();
		new Select(driverOpera.findElement(LocatorsT.selectServiceInOrder)).selectByIndex(1);
		new Select(driverOpera.findElement(LocatorsT.selectSaloonInOrder)).selectByIndex(2);

		driverOpera.findElement(LocatorsT.buttonPriceInServices).click();

		HelpMethodsT.downloadPicture(driverOpera, Parametrs“.ownpicture);

		new WebDriverWait(driverOpera, 30).until(ExpectedConditions.visibilityOfElementLocated(LocatorsT.buttonOrder));

		driverOpera.findElement(LocatorsT.buttonOrder).click();

		String artist = pageObj.selectArtist(4);
		String salon = pageObj.salonHide();
		String date = pageObj.selectDate2(27, 7, 12, 10);
		pageObj.orderOrder();

		HelpMethodsT.assertOrdersTable(numberOfOrders, driverOpera, salon, artist, date);

		driverOpera.findElement(LocatorsT.buttonLogOut).click();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {
		driverOpera.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}