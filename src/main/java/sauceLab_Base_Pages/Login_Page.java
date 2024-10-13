package sauceLab_Base_Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import sauceLab_Utilities.SeleniumWrapper;

public class Login_Page extends SeleniumWrapper {
	public WebDriver driver;
	public Product_Page ProductPage;
	

	public Login_Page(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
@FindBy(id="user-name")
WebElement usernameTextbox;

@FindBy(id="password")
WebElement passwordTextbox;

@FindBy(id="login-button")
WebElement loginButton;

@FindBy(className="product_label")
WebElement productsLabel;

@FindBy(className="bm-burger-button")
WebElement mainMenuButton;

@FindBy(linkText="All Items")
WebElement allItemsLink;

@FindBy(linkText="About")
WebElement aboutLink;

@FindBy(linkText="Logout")
WebElement logoutLink;

@FindBy(linkText="Reset App State")
WebElement resetAppStateLink;

@FindBy(xpath="//button[text()='Close Menu']")
WebElement closeButton;

@FindBy(tagName="h3")
WebElement loginError;

public Product_Page loginApplication(String username, String password) {
	waitForElementToAppear(usernameTextbox);
	usernameTextbox.sendKeys(username);
	passwordTextbox.sendKeys(password);
	loginButton.click();
	waitForElementToAppear(productsLabel);
	ProductPage = new Product_Page(driver);
	return ProductPage;
	
}

public void resetAppState() {
	mainMenuButton.click();
	waitForElementToAppear(resetAppStateLink);
	resetAppStateLink.click();
}

public void logoutApplication() {
	mainMenuButton.click();
	waitForElementToAppear(logoutLink);
	logoutLink.click();		
	
}

public void closeMenu() {
	closeButton.click();
}

public void goToAllItems() {
	mainMenuButton.click();
	waitForElementToAppear(allItemsLink);
	allItemsLink.click();
	waitForElementToAppear(productsLabel);
	}

public void verifyLoginError() {
	String errorMessage = loginError.getText();
	String error = errorMessage.split("\"")[1];
	System.out.println(error);
	Assert.assertEquals(error, "Sorry, this user has been locked out.");
}

}
