package graduateprojectpackage;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ArtistInOrder {
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

	  @Test (groups = { "Critical", "Order Via Tab"}, dependsOnGroups = {"Tattoage"} )
	  public void testSizes() throws Exception {
	    driverOpera.get(ParametrsÒ.baseUrl + "/");
	    HelpMethodsT.insertNamePassword(ParametrsÒ.emmail, ParametrsÒ.password, driverOpera);
	    int numberOfOrders= HelpMethodsT.countOrders(driverOpera);
	    driverOpera.get(ParametrsÒ.baseUrl + "/");
	    driverOpera.findElement(LocatorsT.calculatorInOrder).click();
	    driverOpera.findElement(LocatorsT.artistsInOrder).click();
	
	    new Select(driverOpera.findElement(LocatorsT.artist3)).selectByIndex(3);
	   
	    driverOpera.findElement(LocatorsT.calculate2).click();
	    
	    
	    HelpMethodsT.downloadPicture(driverOpera, "d:\\image2.jpg");	    
	   
	    
	    new WebDriverWait(driverOpera, 30)
		.until(ExpectedConditions.visibilityOfElementLocated(LocatorsT.buttonOrder));
		
	    driverOpera.findElement(LocatorsT.buttonOrder).click();
	    int select = 5;
	    new Select(driverOpera.findElement(LocatorsT.selectArtistInOrder)).selectByIndex(select);
	    String artist =  driverOpera.findElement(By.xpath("//select[@id=\"artist_select\"]/option[@value=\""+ select +"\"]")).getText();
	    String salon = new String(driverOpera.findElement(By.id("saloon_hide")).getText());
	    String date = "10-03 10:10";

	    new Select(driverOpera.findElement(LocatorsT.selectDay)).selectByIndex(10);
	    new Select(driverOpera.findElement(LocatorsT.selectMonth)).selectByIndex(3);
	    new Select(driverOpera.findElement(LocatorsT.selectHour)).selectByIndex(11);
	    new Select(driverOpera.findElement(LocatorsT.selectMinute)).selectByIndex(3);
	    
	    WebElement buttonOrder= driverOpera.findElement(LocatorsT.buttonOrder);
		Actions action = new Actions (driverOpera);
		action.moveToElement(buttonOrder).click().perform();
		//ñàëîí íå àññåðòèòñÿ
		HelpMethodsT.assertOrdersTable(numberOfOrders, driverOpera, "-",artist, date);
		
	    driverOpera.findElement(By.linkText("Logout")).click();
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

