package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;

public class LoginPage extends BasePage {

	@FindBy(xpath = "//input[@name='email']")
	WebElement userName;

	@FindBy(xpath = "//input[@name='password']")
	WebElement passWord;

	@FindBy(xpath = "//div[text()='Login']")
	WebElement loginButton;

	@FindBy(xpath = "//a[text()='Forgot your password?']")
	WebElement forgotPassWordLink;
	
	@FindBy(xpath = "//div[text()='Something went wrong...']")
	WebElement error;

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	public void cogmentoLogin() {
		userName.sendKeys(prop.getProperty("username"));
		passWord.sendKeys(prop.getProperty("password"));
		loginButton.click();
	}

	public void clickForgotPassWordLink() {
		forgotPassWordLink.click();
	}

	public void verifyForgotPassWordLinkDisplayed() {
		forgotPassWordLink.isDisplayed();
	}

	public void cogmentoLoginWithExcel(String username, String password) throws InterruptedException {
		userName.sendKeys(username);
		passWord.sendKeys(password);
		loginButton.click();
	}
	
	public void verifyErrorForInvalidCred() throws InterruptedException {
		Thread.sleep(3000);
		String actualError = error.getText();
		String expectedError = "Something went right";
		assert actualError.equalsIgnoreCase(expectedError);
	}

}
