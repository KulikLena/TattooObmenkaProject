package graduateprojectpackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class OrderPage {

	private final WebDriver dr;

	public OrderPage(WebDriver dr) {
		this.dr = dr;
	}

	public String selectArtist(int select) {
		new Select(dr.findElement(LocatorsT.selectArtistInOrder)).selectByIndex(select);
		WebElement artist = dr
				.findElement(By.xpath("//select[@id=\"artist_select\"]/option[@value=\"" + select + "\"]"));
		return artist.getText();
	}

	public String selectSalon(int ind) {
		new Select(dr.findElement(LocatorsT.selectSaloonInOrder)).selectByIndex(ind);
		WebElement salon = dr.findElement(By.xpath("//select[@id=\"saloon_select\"]/option[@value=\"" + ind + "\"]"));
		return salon.getText();
	}

	public String salonHide() {
		return new String(dr.findElement(LocatorsT.salonHide).getText());
	}

	public String selectDate(String date) {
		dr.findElement(LocatorsT.selectDateInOrder).clear();
		dr.findElement(LocatorsT.selectDateInOrder).sendKeys(date);
		return date;
	}

	public String selectDate2(int day, int month, int hour, int minute) {
		new Select(dr.findElement(LocatorsT.selectDay)).selectByIndex(day);
		new Select(dr.findElement(LocatorsT.selectMonth)).selectByIndex(month);
		new Select(dr.findElement(LocatorsT.selectHour)).selectByIndex(hour + 1);
		new Select(dr.findElement(LocatorsT.selectMinute)).selectByIndex(minute / 5 + 1);
		OrderPage page = new OrderPage(dr);
		String date = page.toString(day) + "-" + page.toString(month) + " " + page.toString(hour) + ":"
				+ page.toString(minute);
		return date;
	}

	public String toString(int parametr) {
		String s;
		if (parametr < 10)
			s = ("0" + parametr);
		else
			s = "" + parametr;
		return s;
	}

	public void orderOrder() {
		WebElement buttonOrder = dr.findElement(LocatorsT.buttonOrder);
		Actions action = new Actions(dr);
		action.moveToElement(buttonOrder).click().perform();
	}

}
