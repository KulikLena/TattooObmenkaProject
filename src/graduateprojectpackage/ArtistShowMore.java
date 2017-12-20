package graduateprojectpackage;


import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.ui.Select;

public class ArtistShowMore {
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

	@Test (groups = {  "Show More" }, dependsOnGroups = {"Critical"})
	public void testArtist() throws Exception {
		driverOpera.get(Parametrs“.baseUrl + "/");
		HelpMethodsT.insertNamePassword(Parametrs“.emmail, Parametrs“.password, driverOpera);
		int numberOfOrders = HelpMethodsT.countOrders(driverOpera);
		driverOpera.findElement(LocatorsT.menuTatouage).click();
		driverOpera.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driverOpera.findElement(LocatorsT.selectArtist).click();
		driverOpera.findElement(LocatorsT.selectArtistSeb).click();
		driverOpera.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driverOpera.findElement(LocatorsT.selectRealiste).click();
		driverOpera.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
				
		int select = 7;
		new Select(driverOpera.findElement(LocatorsT.selectArtistInOrder)).selectByIndex(select);
		String artist = driverOpera
				.findElement(By.xpath("//select[@id=\"artist_select\"]/option[@value=\"" + select + "\"]")).getText();
		String date = "07/25/2018 9:00 AM";

		String salon = new String(driverOpera.findElement(LocatorsT.salonHide).getText());
		driverOpera.findElement(LocatorsT.selectDateInOrder).clear();
		driverOpera.findElement(LocatorsT.selectDateInOrder).sendKeys(date);
		
		//driverOpera.findElement(LocatorsT.buttonChangePicture).click();
		//HelpMethodsT.downloadPicture(driverOpera);

		 WebElement buttonOrder= driverOpera.findElement(LocatorsT.buttonOrder);
			Actions action = new Actions (driverOpera);
			action.moveToElement(buttonOrder).click().perform();
		
		HelpMethodsT.assertOrdersTable(numberOfOrders, driverOpera, salon,artist, date);
		
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
