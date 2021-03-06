package graduateprojectpackage;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

public class Order {

	private WebDriver driverOpera;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		OperaOptions operaOptions = new OperaOptions();
		operaOptions.setBinary("c:\\Program Files\\Opera\\49.0.2725.64\\opera.exe");
		System.setProperty("webdriver.opera.driver", "d:\\operadriver.exe");
		driverOpera = new OperaDriver(operaOptions);
		driverOpera.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test(groups = { "Critical", "Order" })
	public void testOrder() throws Exception {
		driverOpera.get(ParametrsТ.baseUrl + "/");
		HelpMethodsT.insertNamePassword(ParametrsТ.emmail, ParametrsТ.password, driverOpera);
		int numberOfOrders = HelpMethodsT.countOrders(driverOpera);
		driverOpera.findElement(LocatorsT.buttonMainOrder).click();

		OrderPage pageObj = new OrderPage(driverOpera);
		String artist = pageObj.selectArtist(8);
		String salon = pageObj.salonHide();
		String date = pageObj.selectDate2(21, 8, 14, 50);
		pageObj.orderOrder();

		// салон не ассертится
		HelpMethodsT.assertOrdersTable(numberOfOrders, driverOpera, salon, artist, date);

		driverOpera.findElement(By.linkText("Logout")).click();
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
