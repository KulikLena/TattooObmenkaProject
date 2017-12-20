package graduateprojectpackage;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.ui.Select;

public class Sizes {
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
  public void testSizes() throws Exception {
    driverOpera.get(Parametrs“.baseUrl + "/");
    HelpMethodsT.insertNamePassword(Parametrs“.emmail, Parametrs“.password, driverOpera);
    int numberOfOrders= HelpMethodsT.countOrders(driverOpera);
    driverOpera.findElement(LocatorsT.menuTatouage).click();
   	driverOpera.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
   	driverOpera.findElement(LocatorsT.selectSizes).click();
    driverOpera.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    String s = driverOpera.findElement(LocatorsT.priceInTableSizes).getText();
    driverOpera.findElement(LocatorsT.selectFirstTraits).click();
    driverOpera.findElement(LocatorsT.buttonOrderTattoo).click();
  
    driverOpera.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    assertEquals(driverOpera.findElement(LocatorsT.priceInOrder).getText().replaceAll("\\D+", "").trim(), s);
    int select = 2;
    new Select(driverOpera.findElement(LocatorsT.selectArtistInOrder)).selectByIndex(select);
    String artist =  driverOpera.findElement(By.xpath("//select[@id=\"artist_select\"]/option[@value=\""+ select +"\"]")).getText();
    String salon = new String(driverOpera.findElement(By.id("saloon_hide")).getText());
    String date = "02/24/2018 6:00 AM";
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

