package graduateprojectpackage;

import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ShowMoreTests {
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
		pageObj = new OrderPage(driverOpera);
		
	}

	@Test  (groups = { "Critical", "Show More"})
	public void testArtistShowMore() throws Exception {
		driverOpera.get(Parametrs“.baseUrl + "/");
		HelpMethodsT.insertNamePassword(Parametrs“.emmail, Parametrs“.password, driverOpera);
		int numberOfOrders = HelpMethodsT.countOrders(driverOpera);
		driverOpera.findElement(LocatorsT.menuTatouage).click();
		driverOpera.findElement(LocatorsT.selectArtist).click();
		driverOpera.findElement(LocatorsT.selectArtistSeb).click();
		driverOpera.findElement(LocatorsT.selectRealiste).click();

		String artist = pageObj.selectArtist(7);
		String salon = pageObj.salonHide();
		String date = pageObj.selectDate("07/25/2018 9:00 AM");
		pageObj.orderOrder();

		HelpMethodsT.assertOrdersTable(numberOfOrders, driverOpera, salon, artist, date);

		driverOpera.findElement(LocatorsT.buttonLogOut).click();
	}
	@Test (groups = { "Critical", "Show More"})
	public void testSalonShowMore() throws Exception {
		driverOpera.get(Parametrs“.baseUrl + "/");
		HelpMethodsT.insertNamePassword(Parametrs“.emmail, Parametrs“.password, driverOpera);
		int numberOfOrders = HelpMethodsT.countOrders(driverOpera);
		driverOpera.findElement(LocatorsT.menuTatouage).click();
		driverOpera.findElement(LocatorsT.selectSalon).click();
		driverOpera.findElement(LocatorsT.salonShowMore).click();
		driverOpera.findElement(LocatorsT.buttonOrderTattoo).click();

		String salon = driverOpera.findElement(LocatorsT.orderedSalon).getText();
		String date = pageObj.selectDate("11/23/2018 10:00 AM");
		pageObj.orderOrder();

		HelpMethodsT.assertOrdersTable(numberOfOrders, driverOpera, salon, "-", date);

		driverOpera.findElement(LocatorsT.buttonLogOut).click();
	}
	@Test (groups = { "Critical", "Show More"})
	public void testPicsShoMore() throws Exception {
		driverOpera.get(Parametrs“.baseUrl + "/");
		HelpMethodsT.insertNamePassword(Parametrs“.emmail, Parametrs“.password, driverOpera);
		int numberOfOrders = HelpMethodsT.countOrders(driverOpera);
		driverOpera.findElement(LocatorsT.menuTatouage).click();
		driverOpera.findElement(LocatorsT.selectGalery).click();

		driverOpera.findElement(LocatorsT.selectRealiste2).click();

		driverOpera.findElement(LocatorsT.buttonOrderTattoo).click();
		
		String artist = pageObj.selectArtist(4);
		String salon = pageObj.salonHide();
		String date = pageObj.selectDate("01/30/2018 12:00 AM");
		pageObj.orderOrder();

		HelpMethodsT.assertOrdersTable(numberOfOrders, driverOpera, salon, artist, date);

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
}
