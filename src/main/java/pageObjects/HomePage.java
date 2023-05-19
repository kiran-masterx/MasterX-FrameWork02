package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;

public class HomePage extends BasePage {

	@FindBy(xpath = "//div[@class='header item']")
	WebElement cogmentoLogo;
	
	@FindBy(xpath = "//i[@class='money icon']")
	WebElement dealsIcon;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public boolean isCogmentoLogoDisplayed() {
		return cogmentoLogo.isDisplayed();
	}
	
	public void navigateDealsPage() {
		dealsIcon.click();
	}

}
