package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;

public class DealsPage extends BasePage {

	@FindBy (xpath= "//i[@class='edit icon']")
	WebElement createDealButton;
	
	@FindBy(xpath = "//input[@name='title']")
	WebElement title;

	@FindBy(xpath = "//label[text()='Assigned To']/parent::div/child::div")
	WebElement assignedToDropdown;

	@FindBy(xpath = "//label[text()='Assigned To']/parent::div/child::div//span[text()='Test User']")
	WebElement testUser;
	
	@FindBy(xpath = "//div[@name='company']//input")
	WebElement company;
	
	@FindBy(xpath = "//div[@name='company']//div//div[1]")
	WebElement firstCompany;
	
	@FindBy(xpath = "//i[@class='save icon']")
	WebElement saveIcon;
	
	public DealsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void clickCreateDealButton() {
		createDealButton.click();
	}

	public void createDeal(String title1, String company1) throws InterruptedException {
		title.sendKeys(title1);
		assignedToDropdown.click();
		testUser.click();
		company.sendKeys(company1);
		Thread.sleep(2000);
		firstCompany.click();
		saveIcon.click();
		Thread.sleep(5000);
	}
}
