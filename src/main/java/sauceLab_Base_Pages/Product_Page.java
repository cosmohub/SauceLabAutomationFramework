package sauceLab_Base_Pages;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import sauceLab_Utilities.SeleniumWrapper;

public class Product_Page extends SeleniumWrapper {
	public WebDriver driver;
	public Cart_Page Cart_Page;
	
	public Product_Page(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="shopping_cart_container")
	WebElement shoppingCartButton;
	
	@FindBy(xpath="//span[contains(@class,'shopping_cart_badge')]")
	WebElement cartProductCount;
	
	@FindBy(xpath="//div[contains(@class,'inventory_item_name')]")
	List<WebElement> products;
	
	@FindBy(xpath="//div[contains(@class,'inventory_item_name')]/../../..//div[@class='inventory_item_price']")
	List<WebElement> productsPriceOption;
	
	@FindBy(xpath="//div[contains(@class,'inventory_item_name')]/../../..//button[contains(@class,'btn_inventory')]")
	List<WebElement> addToCartButton;
	
	@FindBy(className="product_sort_container")
	WebElement sortByDropdown;
	
	
	public void verifyProductAndPriceDisplayed(String product, String price) {
		driver.findElement(By.xpath("//div[contains(@class,'inventory_item_name') and contains(text(),'"+product+"')]")).isDisplayed();
		String actualPrice = driver.findElement(By.xpath("//div[contains(@class,'inventory_item_name') and contains(text(),'"+product+"')]/../../..//div[@class='inventory_item_price']")).getText();	
		Assert.assertTrue(actualPrice.contains(price));
	}
	
	public void addProductToCart(String product) {
		driver.findElement(By.xpath("//div[contains(@class,'inventory_item_name') and contains(text(),'"+product+"')]/../../..//button[contains(text(),'ADD TO CART')]")).click();
	}
	
	public void verifyCartCount(String i) {
		waitForElementToAppear(cartProductCount);
		String actualCount = cartProductCount.getText();
		Assert.assertEquals(actualCount,i);
		
	}
	
	public Cart_Page goToShoppingCart() {
		cartProductCount.click();
		Cart_Page = new Cart_Page(driver);
		return Cart_Page;
		
	}
	
	public void verifyUnavailabilityOfCartCount() {
		waitForElementToDisappear(cartProductCount);
	}
	
	public void verifyProductDetailsDisplay() {
		waitForElementToAppear(sortByDropdown);
		int productCount = products.size();
		Assert.assertEquals(productCount, 6);
		int priceCount = productsPriceOption.size();
		Assert.assertEquals(priceCount, 6);
		int addToCartCount = addToCartButton.size();
		Assert.assertEquals(addToCartCount, 6);	
		
	}
	
	public void verifySortByOptionsValues() {
		sortByDropdown.isDisplayed();
		Select select_obj = new Select(sortByDropdown);
		List<WebElement> options = select_obj.getOptions();
		String[] expectedValues = {"Name (A to Z)", "Name (Z to A)","Price (low to high)","Price (high to low)"};
		for (int i=0;i<options.size();i++) {
			String actualValue = options.get(i).getText();
			String expectedValue = expectedValues[i];
			Assert.assertEquals(actualValue, expectedValue);
		}
	}
}

