package graduateprojectpackage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class OrderTests {
	private WebDriver driverOpera;
	private StringBuffer verificationErrors = new StringBuffer();
	private OrderPage pageObj;

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		OperaOptions operaOptions = new OperaOptions();
		operaOptions.setBinary("c:\\Program Files\\Opera\\49.0.2725.64\\opera.exe");
		System.setProperty("webdriver.opera.driver", "d:\\operadriver.exe");
		driverOpera = new OperaDriver(operaOptions);
		driverOpera.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}

	@BeforeClass
	public void setUpPage() throws Exception {
		pageObj = new OrderPage(driverOpera);
	}

	@Test(priority=1, groups = { "Critical", "Tattoage"})
	public void testArtist() throws Exception {
		driverOpera.get(Parametrs“.baseUrl + "/");
		HelpMethodsT.insertNamePassword(Parametrs“.emmail, Parametrs“.password, driverOpera);
		int numberOfOrders = HelpMethodsT.countOrders(driverOpera);
		driverOpera.findElement(LocatorsT.menuTatouage).click();
		driverOpera.findElement(LocatorsT.selectArtist).click();
		driverOpera.findElement(LocatorsT.buttonOrderTattoo).click();

		String artist = pageObj.selectArtist(1);
		String salon = pageObj.selectSalon(5);
		String date = pageObj.selectDate("04/26/2018 10:00 AM");
		pageObj.orderOrder();

		HelpMethodsT.assertOrdersTable(numberOfOrders, driverOpera, salon, artist, date);

		driverOpera.findElement(LocatorsT.buttonLogOut).click();
	}

	@Test(priority=2, groups = { "Critical", "Tattoage"})
	public void testSalon() throws Exception {
		driverOpera.get(Parametrs“.baseUrl + "/");
		HelpMethodsT.insertNamePassword(Parametrs“.emmail, Parametrs“.password, driverOpera);
		int numberOfOrders = HelpMethodsT.countOrders(driverOpera);
		driverOpera.findElement(LocatorsT.menuTatouage).click();
		driverOpera.findElement(LocatorsT.selectSalon).click();
		driverOpera.findElement(LocatorsT.buttonOrderTattoo).click();

		String salon = driverOpera.findElement(LocatorsT.orderedSalon).getText();
		String date = pageObj.selectDate("12/30/2017 7:00 AM");
		pageObj.orderOrder();

		HelpMethodsT.assertOrdersTable(numberOfOrders, driverOpera, salon, "-", date);

		driverOpera.findElement(LocatorsT.buttonLogOut).click();
	}

	@Test(priority=3, groups = { "Critical", "Tattoage"})
	public void testPics() throws Exception {
		driverOpera.get(Parametrs“.baseUrl + "/");
		HelpMethodsT.insertNamePassword(Parametrs“.emmail, Parametrs“.password, driverOpera);
		int numberOfOrders = HelpMethodsT.countOrders(driverOpera);
		driverOpera.findElement(LocatorsT.menuTatouage).click();
		driverOpera.findElement(LocatorsT.selectGalery).click();

		driverOpera.findElement(LocatorsT.buttonOrderTattoo).click();

		String artist = pageObj.selectArtist(3);
		String salon = pageObj.salonHide();
		String date = pageObj.selectDate("01/30/2018 12:00 AM");
		pageObj.orderOrder();

		HelpMethodsT.assertOrdersTable(numberOfOrders, driverOpera, salon, artist, date);

		driverOpera.findElement(LocatorsT.buttonLogOut).click();
	}

	@Test(priority=4,groups = { "Critical", "Tattoage"})
	public void testSizes() throws Exception {
		driverOpera.get(Parametrs“.baseUrl + "/");
		HelpMethodsT.insertNamePassword(Parametrs“.emmail, Parametrs“.password, driverOpera);
		int numberOfOrders = HelpMethodsT.countOrders(driverOpera);
		driverOpera.findElement(LocatorsT.menuTatouage).click();
		driverOpera.findElement(LocatorsT.selectSizes).click();
		String s = driverOpera.findElement(LocatorsT.priceInTableSizes).getText();
		driverOpera.findElement(LocatorsT.selectFirstTraits).click();
		driverOpera.findElement(LocatorsT.buttonOrderTattoo).click();

		assertEquals(driverOpera.findElement(LocatorsT.priceInOrder).getText().replaceAll("\\D+", "").trim(), s);

		String artist = pageObj.selectArtist(2);
		String salon = pageObj.salonHide();
		String date = pageObj.selectDate("02/24/2018 6:00 AM");
		pageObj.orderOrder();

		HelpMethodsT.assertOrdersTable(numberOfOrders, driverOpera, salon, artist, date);

		driverOpera.findElement(LocatorsT.buttonLogOut).click();
	}

	@Test(priority=5, groups = { "Critical", "Tattoage"})
	public void testCalculator() throws Exception {
		driverOpera.get(Parametrs“.baseUrl + "/");
		HelpMethodsT.insertNamePassword(Parametrs“.emmail, Parametrs“.password, driverOpera);
		int numberOfOrders = HelpMethodsT.countOrders(driverOpera);
		driverOpera.findElement(LocatorsT.menuTatouage).click();
		driverOpera.findElement(LocatorsT.selectCalculator).click();
		HelpMethodsT.downloadPicture(driverOpera, "d:\\image1.jpg");

		new WebDriverWait(driverOpera, 30).until(ExpectedConditions.visibilityOfElementLocated(LocatorsT.buttonOrder));

		driverOpera.findElement(LocatorsT.buttonOrder).click();
		
		String artist = pageObj.selectArtist(4);
		String salon = pageObj.salonHide();
		String date = pageObj.selectDate2(25, 3, 14, 5);
		pageObj.orderOrder();

		HelpMethodsT.assertOrdersTable(numberOfOrders, driverOpera, salon, artist, date);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driverOpera.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}
