package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;

public class ForgotPasswordPage extends BasePage {
	@FindBy(xpath = "//h2[@class='ui blue header']")
	WebElement forgotMyPasswordHeading;

	public ForgotPasswordPage() {
		PageFactory.initElements(driver, this);
	}

	public void verifyforgotMyPasswordHeading() {
		String actualHeading = forgotMyPasswordHeading.getText();
		String expectedHeading = "Forgot ";
		System.out.println(actualHeading + "   " + expectedHeading);
		assert actualHeading.equalsIgnoreCase(expectedHeading);
	}

}
