package testCases;

import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BasePage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import utils.ExcelDataReader;

public class LognTestWithExcel extends BasePage {

	@BeforeMethod
	public void setUp() {
		launchBrowser();
	}

	@Test(dataProvider = "cogmentoData", dataProviderClass = ExcelDataReader.class)
	public void login(String username, String password) throws InterruptedException {
		LoginPage loginPage = new LoginPage();
		loginPage.cogmentoLoginWithExcel(username, password);
		try {
			HomePage homePage = new HomePage();
			homePage.isCogmentoLogoDisplayed();
		} catch (NoSuchElementException e) {
			loginPage.verifyErrorForInvalidCred();
		}
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
