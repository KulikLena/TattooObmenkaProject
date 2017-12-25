package graduateprojectpackage;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class Refine {

	public static void screenShot(String folder, String format, WebDriver driver)
			throws IOException, InterruptedException {
		Thread.sleep(3000);
		// driver.findElementByTag("body").saveScreenshot(0);
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String name = folder + (new Date()).getTime() + format;
		FileUtils.copyFile(file, new File(name));
		Reporter.log("ScreenFile=" + name);
	}

}
