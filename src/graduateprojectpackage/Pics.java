package graduateprojectpackage;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.ui.Select;

public class Pics {
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

  @Test (groups = { "Critical", "Tattoage" }, dependsOnGroups = {"Order"})
  public void testPics() throws Exception {
    driverOpera.get(Parametrs“.baseUrl + "/");
    HelpMethodsT.insertNamePassword(Parametrs“.emmail, Parametrs“.password, driverOpera);
    int numberOfOrders= HelpMethodsT.countOrders(driverOpera);
    driverOpera.findElement(LocatorsT.menuTatouage).click();
	driverOpera.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driverOpera.findElement(LocatorsT.selectGalery).click();
    
    driverOpera.findElement(By.linkText("Order tattoo")).click();
    new Select(driverOpera.findElement(LocatorsT.selectArtistInOrder)).selectByIndex(5);
    int select = 7;
	String artist = driverOpera
			.findElement(By.xpath("//select[@id=\"artist_select\"]/option[@value=\"" + select + "\"]")).getText();
	String date = "01/30/2018 12:00 AM";

	String salon = new String(
			driverOpera.findElement(By.id("saloon_hide")).getText());
    
     driverOpera.findElement(LocatorsT.selectDateInOrder).clear();
    driverOpera.findElement(LocatorsT.selectDateInOrder).sendKeys(date);
     
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
