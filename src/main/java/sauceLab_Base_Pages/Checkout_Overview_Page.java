package sauceLab_Base_Pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import sauceLab_Utilities.SeleniumWrapper;

public class Checkout_Overview_Page extends SeleniumWrapper {
	public WebDriver driver;
	public Order_Finish_Page Order_Finish_Page;
	
	public Checkout_Overview_Page(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	

	@FindBy(xpath="(//div[@class='summary_value_label'])[1]")
	WebElement paymentInfoLabel;
	
	@FindBy(xpath="(//div[@class='summary_value_label'])[2]")
	WebElement shippingInfoLabel;
	
	@FindBy(className="summary_subtotal_label")
	WebElement itemTotalValue;
	
	@FindBy(className="summary_tax_label")
	WebElement itemTaxValue;
	
	@FindBy(className="summary_total_label")
	WebElement totalValue;
	
	@FindBy(linkText="CANCEL")
	WebElement cancelButton;
	
	@FindBy(linkText="FINISH")
	WebElement finishButton;
	
	public void verifyPaymentInfo() {
		String paymentInfo =  paymentInfoLabel.getText();
		Assert.assertEquals(paymentInfo,"SauceCard #31337");
	}
	
	public void verifyShippingInfo() {
		String shippingInfo =  shippingInfoLabel.getText();
		Assert.assertEquals(shippingInfo, "FREE PONY EXPRESS DELIVERY!");
	}
	
	public void verifyItemPrice(String price) {
		String itemTotal = itemTotalValue.getText();
		String itemActualValue = itemTotal.split(" ")[2];
		Assert.assertEquals(itemActualValue, "$"+price);
	}

	public void verifyTaxTotal(String tax) {
		String taxTotal = itemTaxValue.getText();
		String taxActualValue = taxTotal.split(" ")[1];
		Assert.assertEquals(taxActualValue, "$"+tax);
	}
	
	public void verifyItemTotal(String totalPrice) {
		String itemTotal = totalValue.getText();
		String itemTotalActual = itemTotal.split(" ")[1];
		Assert.assertEquals(itemTotalActual, "$"+totalPrice);
	}
	
	public sauceLab_Base_Pages.Order_Finish_Page finishCheckoutProcess() {
		finishButton.click();
		Order_Finish_Page Order_Finish_Page = new Order_Finish_Page(driver);
		return Order_Finish_Page;
	}
}
