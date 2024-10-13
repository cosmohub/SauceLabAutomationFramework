package sauceLab_Base_Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sauceLab_Utilities.SeleniumWrapper;

public class Checkout_Page extends SeleniumWrapper {
	public WebDriver driver;
	public Checkout_Overview_Page Checkout_Overview_Page;
	
	public Checkout_Page(WebDriver driver)  {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	

	@FindBy(id="first-name")
	WebElement firstNameTextbox;
	
	@FindBy(id="last-name")
	WebElement lastNameTextbox;
	
	@FindBy(id="postal-code")
	WebElement postalCodeTextbox;
	
	@FindBy(linkText="CANCEL")
	WebElement cancelButton;
	
	@FindBy(css="input[value='CONTINUE']")
	WebElement continueButton;
	
	public Checkout_Overview_Page complete_checkout_process(String firstName, String lastName, String postalcode) {
		waitForElementToAppear(firstNameTextbox);
		firstNameTextbox.sendKeys(firstName);
		lastNameTextbox.sendKeys(lastName);
		postalCodeTextbox.sendKeys(postalcode);
		continueButton.click();
		Checkout_Overview_Page = new Checkout_Overview_Page(driver);
		return Checkout_Overview_Page;
		
	}

}
