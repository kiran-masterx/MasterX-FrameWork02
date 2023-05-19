package testCases;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BasePage;
import pageObjects.ForgotPasswordPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
@Test(groups = "Smoke")
public class LoginPageTest extends BasePage {

	/*
	 * Verify login is successful Verify the forgot password link is displayed
	 * Verify the forgot password link is working
	 */

	@BeforeMethod
	public void setUp() {
		launchBrowser();
	}


	@Test(priority = 1)
	public void verifyLoginSuccessful() throws IOException {
		test = extent.createTest("verifyLoginSuccessful", "To verify the login is successful by seeing the logo");
		LoginPage loginPage = new LoginPage();
		loginPage.cogmentoLogin();
		HomePage homePage = new HomePage();
		boolean logo = homePage.isCogmentoLogoDisplayed();
		System.out.println(logo);
		captureScreenshots("verifyLoginSuccessful");
	}

	@Test(priority = 2)
	public void verifyForgotPasswordLinkDisplayed() throws IOException {
		test = extent.createTest("verifyForgotPasswordLinkDisplayed", "To verify the link is displayed");
		LoginPage loginPage = new LoginPage();
		loginPage.verifyForgotPassWordLinkDisplayed();
		captureScreenshots("verifyForgotPasswordLinkDisplayed");
	}

	@Test(priority = 3)
	public void verifyForgotPasswordLinkWorking() throws IOException {
		test = extent.createTest("verifyForgotPasswordLinkWorking");
		LoginPage loginPage = new LoginPage();
		loginPage.clickForgotPassWordLink();
		captureScreenshots("verifyForgotPasswordLink");
		ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage();
		forgotPasswordPage.verifyforgotMyPasswordHeading();
		captureScreenshots("verifyForgotPasswordLinkWorking");
	}
	
	@Test(priority = 3)
	public void verifyForgotPasswordLink() throws IOException {
		test = extent.createTest("verifyForgotPasswordLinkWorking");
		LoginPage loginPage = new LoginPage();
		loginPage.clickForgotPassWordLink();
		captureScreenshots("verifyForgotPasswordLink");
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
