package graduateprojectpackage;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;

import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginMozillaT {

	private static WebDriver driverMozilla;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	
	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "d:\\geckodriver.exe");
		driverMozilla = new FirefoxDriver();

	}

	@BeforeMethod
	public static void base() {
		// âûáðàñûâàåò èñêëþ÷åíèå - ïîìåñòèëà â òðàé/êýò÷
		try {
			driverMozilla.get(ParametrsÒ.baseUrl + "/");
		} catch (InvalidArgumentException e) {
			e.printStackTrace();
		}

		driverMozilla.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test(groups = { "Critical", "Login" }, dataProvider = "myData", dataProviderClass = LoginData.class)
	public void testLogin(TestLoginParametr parameterObject) throws Exception {

		HelpMethodsT.insertNamePassword(parameterObject.login, parameterObject.passwword, driverMozilla);

		switch (parameterObject.result) {
		case "1": {
			assertEquals(driverMozilla.findElement(LocatorsT.buttonHome).getText(), "Home");
			WebElement buttonLogOut = driverMozilla.findElement(LocatorsT.buttonLogOut);
			// driverMozilla.findElement(LocatorsT.buttonLogOut).click();
			Actions action = new Actions(driverMozilla);
			action.moveToElement(buttonLogOut).click().perform();
			new WebDriverWait(driverMozilla, 30).until(ExpectedConditions.visibilityOfElementLocated(LocatorsT.login));

		}
			break;
		case "2":
		case "3":
		case "4":
			assertEquals(driverMozilla.findElement(LocatorsT.alertText).getText(),
					"These credentials do not match our records.");
			try {
				driverMozilla.get(ParametrsÒ.baseUrl + "/");
			} catch (InvalidArgumentException e) {
				e.printStackTrace();
			}
			break;
		case "5":
			assertEquals(driverMozilla.findElement(LocatorsT.alertText).getText(), "The email field is required.");
			try {
				driverMozilla.get(ParametrsÒ.baseUrl + "/");
			} catch (InvalidArgumentException e) {
				e.printStackTrace();
			}
			break;
		case "6":
		case "8":
			assertEquals(driverMozilla.findElement(LocatorsT.alertText).getText(), "The password field is required.");
			try {
				driverMozilla.get(ParametrsÒ.baseUrl + "/");
			} catch (InvalidArgumentException e) {
				e.printStackTrace();
			}
			break;
		case "7":
			assertEquals(driverMozilla.findElement(By.xpath("//li[1]")).getText(), "The email field is required.");
			assertEquals(driverMozilla.findElement(By.xpath("//li[2]")).getText(), "The password field is required.");
			try {
				driverMozilla.get(ParametrsÒ.baseUrl + "/");
			} catch (InvalidArgumentException e) {
				e.printStackTrace();
			}
			break;
		default:
			tearDown();
			System.out.println("There's some problems with input data ");
			break;
		}

	}

	@AfterMethod
	public void afterMethod() {
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driverMozilla.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}


	private boolean isElementPresent(By by) {
		try {
			driverMozilla.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driverMozilla.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driverMozilla.switchTo().alert();
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
