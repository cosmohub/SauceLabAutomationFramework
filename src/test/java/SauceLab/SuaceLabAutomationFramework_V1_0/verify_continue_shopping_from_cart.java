package SauceLab.SuaceLabAutomationFramework_V1_0;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import sauceLab_Base_Pages.Cart_Page;
import sauceLab_Base_Pages.Product_Page;
import sauceLab_Utilities.BaseTest;

public class verify_continue_shopping_from_cart extends BaseTest{
	
	@Test(dataProvider="getTestData")
	public void continueShoppingFromCart(HashMap<String, String> input) {
		String username = input.get("username");
		String password = input.get("password");
		String product1 = input.get("product1");
		String price1 = input.get("product_price1");
		String product2 = input.get("product2");
		String price2 = input.get("product_price2");
				
		Product_Page ProductPage = Login_Page.loginApplication(username, password);
		Login_Page.goToAllItems();
		ProductPage.verifyProductAndPriceDisplayed(product1, price1);
		ProductPage.addProductToCart(product1);
		ProductPage.verifyCartCount("1");
		Cart_Page Cart_Page = ProductPage.goToShoppingCart();
		ProductPage.verifyProductAndPriceDisplayed(product1, price1);
		Cart_Page.continueShopping();
		ProductPage.verifyProductDetailsDisplay();
		ProductPage.verifySortByOptionsValues();
//		ProductPage.addProductToCart(product2);
//		ProductPage.verifyCartCount("2");
//		ProductPage.goToShoppingCart();
//		ProductPage.verifyProductAndPriceDisplayed(product2, price2);
//		Cart_Page.continueShopping();
//		ProductPage.verifyProductDetailsDisplay();
//		ProductPage.verifySortByOptionsValues();
		Login_Page.logoutApplication();
	}
	
	@DataProvider
	public Object[][] getTestData() throws IOException {
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")+"\\main\\resources\\saucaLab_TestData\\SauceLab_Multiple_Purchase_Order_Data.json");
		return new Object[][] {{data.get(0)}};
	}

}
