package sauceLab_Base_Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sauceLab_Utilities.SeleniumWrapper;

public class Cart_Page extends SeleniumWrapper {
	public WebDriver driver;
	public Checkout_Page Checkout_Page;
	
	public Cart_Page(WebDriver driver)
	{
	super(driver);
	this.driver = driver;
	PageFactory.initElements(driver, this);

}
	@FindBy(xpath="//button[text()='REMOVE']")
	WebElement removeButton;
	
	@FindBy(linkText="CHECKOUT")
	WebElement checkoutButton;
	
	@FindBy(linkText="Continue Shopping")
	WebElement continueShoppingButton;
	
	public Checkout_Page gotoCheckout()
	{   
		checkoutButton.click();
		Checkout_Page = new Checkout_Page(driver);
		return Checkout_Page;
	}
	
	public void removeProduct() {
		removeButton.click();
	}
	
	public void continueShopping() {
		waitForElementToAppear(continueShoppingButton);
		continueShoppingButton.click();
		
	}
}
