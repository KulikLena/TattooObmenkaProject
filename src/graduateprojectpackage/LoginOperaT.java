package graduateprojectpackage;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

public class LoginOperaT {

	private WebDriver driverOpera;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		OperaOptions operaOptions = new OperaOptions();
		operaOptions.setBinary("c:\\Program Files\\Opera\\49.0.2725.39\\opera.exe");
		System.setProperty("webdriver.opera.driver", "d:\\operadriver.exe");
		driverOpera = new OperaDriver(operaOptions);
	}

	@BeforeMethod(alwaysRun = true)
	public void setUp() throws Exception {
		// ����������� ���������� - ��������� � ����/����

		try {
			driverOpera.get(Parametrs�.baseUrl + "/");
		} catch (InvalidArgumentException e) {
			e.printStackTrace();
		}

		driverOpera.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test(groups = { "Critical", "Login" }, dataProvider = "myData", dataProviderClass = LoginData.class)
	public void testLogin(TestLoginParametr parameterObject) throws Exception {

		HelpMethodsT.insertNamePassword(parameterObject.login, parameterObject.passwword, driverOpera);

		switch (parameterObject.result) {
		case "1": {
			assertEquals(driverOpera.findElement(LocatorsT.buttonHome).getText(), "Home");
			driverOpera.findElement(LocatorsT.buttonLogOut).click();
		}
			break;
		case "2":
		case "3":
		case "4":
			assertEquals(driverOpera.findElement(LocatorsT.alertText).getText(),
					"These credentials do not match our records.");
			try {
				driverOpera.get(Parametrs�.baseUrl + "/");
			} catch (InvalidArgumentException e) {
				e.printStackTrace();
			}
			break;
		case "5":
			assertEquals(driverOpera.findElement(LocatorsT.alertText).getText(), "The email field is required.");
			try {
				driverOpera.get(Parametrs�.baseUrl + "/");
			} catch (InvalidArgumentException e) {
				e.printStackTrace();
			}
			break;
		case "6":
		case "8":
			assertEquals(driverOpera.findElement(LocatorsT.alertText).getText(), "The password field is required.");
			try {
				driverOpera.get(Parametrs�.baseUrl + "/");
			} catch (InvalidArgumentException e) {
				e.printStackTrace();
			}
			break;
		case "7":
			assertEquals(driverOpera.findElement(By.xpath("//li[1]")).getText(), "The email field is required.");
			assertEquals(driverOpera.findElement(By.xpath("//li[2]")).getText(), "The password field is required.");
			try {
				driverOpera.get(Parametrs�.baseUrl + "/");
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
