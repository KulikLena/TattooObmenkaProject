package graduateprojectpackage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.Reporter;
import org.testng.annotations.*;

import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MapMozillla {
	private WebDriver driver;
	private boolean acceptNextAlert = true;
	private static WebDriverEventListener myevent;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {

		System.setProperty("webdriver.gecko.driver", "d:\\geckodriver.exe");
		myevent = new MyEventListener();
		driver = new EventFiringWebDriver(new FirefoxDriver()).register(myevent);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test (groups = { "Map"}, dependsOnGroups = {"Login"})
	public void testMap() throws Exception {
		try {
			driver.get(Parametrs�.baseUrl + "/");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			HelpMethodsT.insertNamePassword(Parametrs�.emmail, Parametrs�.password, driver);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement web1 = driver.findElement(LocatorsT.menuTatouage);
			Actions action = new Actions(driver);
			action.moveToElement(web1).click().perform();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(LocatorsT.selectSalon).click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> web = driver.findElements(LocatorsT.adressesSalons);
		String[] a = new String[web.size()];
		if (web.isEmpty()) {
			throw new Exception("was bound to fail!!!");
		} else {

			for (Integer i = 1; i < (web.size() + 1); i++) {

				String l = a[(i - 1)] = driver.findElement(By.xpath(".//div[@class=\"tab-content index\"]//div[" + i + "]//em"))
						.getText();
				System.out.println(a[(i - 1)].toString());
				Reporter.log(i+l);
			}

			driver.findElement(LocatorsT.contact).click();

			for (String l : a) {
				int j=1;
				new WebDriverWait(driver, 30)
						.until(ExpectedConditions.visibilityOfElementLocated(LocatorsT.mapInputField));
				driver.findElement(LocatorsT.mapInputField).click();
				driver.findElement(LocatorsT.mapInputField).clear();
				driver.findElement(LocatorsT.mapInputField).sendKeys(l);
				driver.findElement(LocatorsT.mapSearchButton).click();
				Refine.screenShot(Parametrs�.folder, Parametrs�.format, driver);
				try {
					String s = driver.findElement(LocatorsT.mapDescription).getText();
					Reporter.log(j+s);
					System.out.println(s);
				} catch (NoSuchElementException e) {
					myevent.onException(e, driver);
					System.out.println("InvalidAdress");
					continue;
				}
				j++;
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