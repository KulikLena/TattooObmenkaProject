package graduateprojectpackage;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;

import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Map {
	private WebDriver driver;
	private boolean acceptNextAlert = true;
	//private static WebDriverEventListener myevent;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {

		OperaOptions operaOptions = new OperaOptions();
		operaOptions.setBinary("c:\\Program Files\\Opera\\449.0.2725.64\\opera.exe");
		System.setProperty("webdriver.opera.driver", "d:\\operadriver.exe");
		//myevent = new MyEventListener();
		//driver = new EventFiringWebDriver(new OperaDriver(operaOptions)).register(myevent);
		driver = new OperaDriver(operaOptions);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test /*(groups = { "Map"})*/
	public void testMap() throws Exception {
		driver.get(Parametrs“.baseUrl + "/");
		HelpMethodsT.insertNamePassword(Parametrs“.emmail, Parametrs“.password, driver);
		driver.findElement(LocatorsT.menuTatouage).click();
		driver.findElement(LocatorsT.selectSalon).click();
		List<WebElement> web = driver.findElements(LocatorsT.adressesSalons);
		String[] a = new String[web.size()];
		if (web.isEmpty()) {
			throw new Exception("was bound to fail!!!");
		} else {

			for (Integer i = 1; i < (web.size() + 1); i++) {

				a[(i - 1)] = driver.findElement(By.xpath(".//div[@class=\"tab-content index\"]//div[" + i + "]//em"))
						.getText();
				System.out.println(a[(i - 1)].toString());
			}

			driver.findElement(LocatorsT.contact).click();

			for (String l : a) {
				new WebDriverWait(driver, 30)
						.until(ExpectedConditions.visibilityOfElementLocated(LocatorsT.mapInputField));
				driver.findElement(LocatorsT.mapInputField).click();
				driver.findElement(LocatorsT.mapInputField).clear();
				driver.findElement(LocatorsT.mapInputField).sendKeys(l);
				driver.findElement(LocatorsT.mapSearchButton).click();
				try {
					String s = driver.findElement(LocatorsT.mapDescription).getText();
					//Refine.screenShot(Parametrs“.folder, Parametrs“.format, driver);
					System.out.println(s);
				} catch (NoSuchElementException e) {
					// TODO Auto-generated catch block
					System.out.println("InvalidAdress");
					continue;
				}
			}
			driver.findElement(LocatorsT.buttonLogOut).click();
		}
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
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
