package graduateprojectpackage;

import org.openqa.selenium.By;

public class LocatorsT {

	public static By login = By.linkText("Login with E-mail");
	public static By fieldEmail = By.name("email");
	public static By passsword = By.name("password");
	// public static By alertText = By.cssSelector("div.alert.alert-danger");
	public static By alertText = By.cssSelector("li");
	public static By buttonLogin = By.cssSelector("button.btn.btn-primary");
	public static By buttonHome = By.linkText("Home");
	public static By buttonLogOut = By.linkText("Logout");
	public static By buttonMainOrder = By.linkText("Order");
	public static By mapInputField = By.cssSelector("input.ymaps-2-1-56-searchbox-input__input");
	public static By mapSearchButton = By.cssSelector("ymaps.ymaps-2-1-56-searchbox-button-text");
	public static By mapDescription = By
			.cssSelector("ymaps.ymaps-2-1-56-islets_card > ymaps.ymaps-2-1-56-islets_card__description");

	public static By menuTatouage = By.linkText("Tatouage");
	public static By selectArtist = By.linkText("Artists");
	public static By selectSalon = By.linkText("Salons");
	public static By salonShowMore = By.linkText("Show more");
	public static By selectGalery = By.linkText("Gallery");
	public static By selectSizes = By.linkText("Sizes");
	public static By selectCalculator = By.linkText("Calculator");
	public static By priceInTableSizes = By.xpath("//tbody/tr[2]/td[4]");
	public static By priceInOrder = By.xpath("//div[3]/div");
	public static By selectFirstTraits = By.linkText("Traits");
	public static By selectArtistSeb = By.linkText("Seb");
	public static By selectRealiste = By.linkText("Order Realiste");
	public static By selectRealiste2 = By.linkText("Realiste");
	public static By buttonOrderTattoo = By.linkText("Order tattoo");
	public static By selectSaloonInOrder = By.id("saloon_select");
	public static By selectArtistInOrder = By.id("artist_select");

	public static By adressesSalons = By.xpath("//div[@class=\"col-md-6\"]");
	public static By contact = By.linkText("Contact");
	public static By downloadPicture = By.id("download");

	// вкладка сервисы
	public static By services = By.linkText("Services");
	public static By salonsInOrder = By.linkText("Salons");
	public static By artistsInOrder = By.linkText("Artists");
	public static By selectServiceInOrder = By.id("service_select");
	public static By buttonPriceInServices = By.id("price_button");
	public static By calculatorInOrder = By.linkText("Tattoo calculator");
	public static By salons3 = By.id("saloon_select3");
	public static By artist3 = By.id("artist_select_more");
	public static By calculate = By
			.cssSelector("div[id=profile] button[class=\"btn btn-default btn-block btn-danger\"]");
	public static By calculate2 = By
			.cssSelector("div[id=messages] button[class=\"btn btn-default btn-block btn-danger\"]");

	// вкладка Calculator
	public static By selectDay = By.cssSelector("select.day");
	public static By selectMonth = By.cssSelector("select.month");
	public static By selectHour = By.cssSelector("select.hour");
	public static By selectMinute = By.cssSelector("select.minute");

	public static By selectDateInOrder = By.id("dem");
	public static By buttonChangePicture = By.linkText("Change");
	public static By buttonPrintPicture = By.linkText("Print");
	public static By buttonDownloadPicture = By.id("inputImage");
	public static By fieldPrintPicture = By.id("pic");

	public static By buttonSavePicture = By.cssSelector("button.btn.btn-success");
	public static By buttonCalculatePrice = By.id("price_edit");
	public static By buttonOrder = By.name("send");
	
	public static By orderedSalon = By.xpath("//select[@id=\"saloon_select\"]/option[@value=\"1\"]");
	public static By salonHide = By.id("saloon_hide");

}
