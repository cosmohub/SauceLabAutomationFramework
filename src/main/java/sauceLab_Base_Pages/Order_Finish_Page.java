package sauceLab_Base_Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import sauceLab_Utilities.SeleniumWrapper;

public class Order_Finish_Page extends SeleniumWrapper {

	public Order_Finish_Page(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}

	@FindBy(tagName="h2")
	WebElement orderConfirmationMessage;
	
	@FindBy(className="complete-text")
	WebElement orderDispatchMessage;
	
	@FindBy(xpath="//span[contains(@class,'shopping_cart_badge')]")
	WebElement cartProductCount;
	
	public void verifyOrderConfirmation() {
		waitForElementToDisappear(cartProductCount);
		orderConfirmationMessage.isDisplayed();
		String orderMessage = orderConfirmationMessage.getText();
		Assert.assertEquals(orderMessage,"THANK YOU FOR YOUR ORDER");
		String dispatchMessage = orderDispatchMessage.getText();
		Assert.assertEquals(dispatchMessage, "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
		}
}
