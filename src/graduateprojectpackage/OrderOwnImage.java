package graduateprojectpackage;

import java.util.concurrent.TimeUnit;
import org.testng.Reporter;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderOwnImage {
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

	@Test(groups = { "Critical", "Order" }, dependsOnGroups = { "Map" })
	public void testOrderOwnImage() throws Exception {
		driverOpera.get(Parametrs“.baseUrl + "/");
		HelpMethodsT.insertNamePassword(Parametrs“.emmail, Parametrs“.password, driverOpera);
		int numberOfOrders = HelpMethodsT.countOrders(driverOpera);
		driverOpera.findElement(LocatorsT.menuTatouage).click();
		driverOpera.findElement(LocatorsT.selectCalculator).click();

		WebElement web1 = driverOpera.findElement(LocatorsT.buttonDownloadPicture);
		Actions action = new Actions(driverOpera);
		action.moveToElement(web1).click().perform();

		driverOpera.findElement(LocatorsT.buttonDownloadPicture).clear();
		driverOpera.findElement(LocatorsT.buttonDownloadPicture).sendKeys(Parametrs“.ownpicture);

		driverOpera.findElement(By.cssSelector("button[data-method=moveTo]")).click();

		WebElement cropframe = driverOpera.findElement(By.cssSelector("div[class=\"cropper-crop-box\"]"));
		WebElement sourseimage = driverOpera.findElement(By.cssSelector("img[id=\"image\"]"));
		WebElement cropperpoint = driverOpera.findElement(By.cssSelector("span[class=\"cropper-point point-se\"]"));

		Actions crop = new Actions(driverOpera);
		crop.dragAndDrop(cropframe, sourseimage).perform();
		Thread.sleep(3000);
		crop.release(cropframe);
		crop.moveToElement(cropperpoint).click().build().perform();
		crop.dragAndDrop(cropperpoint, driverOpera.findElement(By.cssSelector("button[title=\"Flip Horizontal\"]")))
				.build().perform();
		crop.release(cropperpoint);
		Thread.sleep(3000);

		new WebDriverWait(driverOpera, 30)
				.until(ExpectedConditions.visibilityOfElementLocated(LocatorsT.buttonSavePicture));

		driverOpera.findElement(LocatorsT.buttonSavePicture).click();
		// download

		new WebDriverWait(driverOpera, 30)
				.until(ExpectedConditions.visibilityOfElementLocated(LocatorsT.downloadPicture));
		driverOpera.findElement(LocatorsT.downloadPicture).click();
		new WebDriverWait(driverOpera, 30)
				.until(ExpectedConditions.visibilityOfElementLocated(LocatorsT.buttonCalculatePrice));
		driverOpera.findElement(LocatorsT.buttonCalculatePrice).click();

		new WebDriverWait(driverOpera, 30).until(ExpectedConditions.visibilityOfElementLocated(LocatorsT.buttonOrder));

		driverOpera.findElement(LocatorsT.buttonOrder).click();

		OrderPage pageObj = new OrderPage(driverOpera);
		String artist = pageObj.selectArtist(4);
		String salon = pageObj.salonHide();
		String date = pageObj.selectDate2(25, 3, 14, 5);
		pageObj.orderOrder();

		// Ò‡ÎÓÌ ÌÂ ‡ÒÒÂÚËÚÒˇ
		HelpMethodsT.assertOrdersTable(numberOfOrders, driverOpera, salon, artist, date);
		System.out.println(DownloadImage.CompareImage(Parametrs“.ownpicture, Parametrs“.endpicture));
		Reporter.log(DownloadImage.CompareImage(Parametrs“.ownpicture, Parametrs“.endpicture).toString());
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
