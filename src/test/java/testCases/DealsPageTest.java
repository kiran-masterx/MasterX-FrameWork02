package testCases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BasePage;
import pageObjects.DealsPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import utils.ExcelDataReader;

public class DealsPageTest extends BasePage{

	@BeforeMethod
	public void setUp() {
		launchBrowser();
	}

	@Test(dataProvider = "cogmentoData", dataProviderClass = ExcelDataReader.class)
	public void createDeal(String username, String password, String title, String company, String name) throws InterruptedException {
		LoginPage loginPage = new LoginPage();
		loginPage.cogmentoLoginWithExcel(username, password);
		
		HomePage homePage = new HomePage();
		homePage.navigateDealsPage();
		
		DealsPage dealsPage = new DealsPage();
		dealsPage.clickCreateDealButton();
		dealsPage.createDeal(title, company);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
