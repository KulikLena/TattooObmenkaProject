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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MapMozillla {
	private WebDriver driver;
	private static WebDriverEventListener myevent;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {

		System.setProperty("webdriver.gecko.driver", "d:\\geckodriver.exe");
		myevent = new MyEventListener();
		driver = new EventFiringWebDriver(new FirefoxDriver()).register(myevent);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test(groups = { "Map" })
	public void testMap() throws Exception {
		try {
			driver.get(Parametrs“.baseUrl + "/");
			HelpMethodsT.insertNamePassword(Parametrs“.emmail, Parametrs“.password, driver);
			WebElement web1 = driver.findElement(LocatorsT.menuTatouage);
			Actions action = new Actions(driver);
			action.moveToElement(web1).click().perform();
			driver.findElement(LocatorsT.selectSalon).click();
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

				String l = a[(i - 1)] = driver
						.findElement(By.xpath(".//div[@class=\"tab-content index\"]//div[" + i + "]//em")).getText();
				System.out.println(a[(i - 1)].toString());
				Reporter.log(i + l);
			}

			driver.findElement(LocatorsT.contact).click();

			for (String l : a) {
				int j = 1;
				new WebDriverWait(driver, 30)
						.until(ExpectedConditions.visibilityOfElementLocated(LocatorsT.mapInputField));
				driver.findElement(LocatorsT.mapInputField).click();
				driver.findElement(LocatorsT.mapInputField).clear();
				driver.findElement(LocatorsT.mapInputField).sendKeys(l);
				driver.findElement(LocatorsT.mapSearchButton).click();
				Refine.screenShot(Parametrs“.folder, Parametrs“.format, driver);
				try {
					String s = driver.findElement(LocatorsT.mapDescription).getText();
					Reporter.log(j + s);
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

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}


}
