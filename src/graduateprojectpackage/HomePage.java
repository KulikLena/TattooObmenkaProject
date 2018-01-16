package graduateprojectpackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

public class HomePage {
	private final WebDriver dr;

	public HomePage(WebDriver dr) {
		this.dr = dr;
	}

	private SoftAssert softAssert;

	public SoftAssert getSoftAssert() {
		return softAssert;
	}

    public void getFailedRequirements(SoftAssert as) {
    	 	as.assertAll();
    }


	public void verifyText(SoftAssert as, String actualValue, String expectedValue, String requirement) {
		as. assertTrue(actualValue.equals(expectedValue), requirement);
	}
	
	

}
