package SauceLab.SuaceLabAutomationFramework_V1_0;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import sauceLab_Base_Pages.Product_Page;
import sauceLab_Utilities.BaseTest;

public class verify_all_items_link_test extends BaseTest{
	
	@Test(dataProvider="getTestData")
	public void verifyAllItemsLink(HashMap<String, String> input) {
	String username = input.get("username");
	String password = input.get("password");
	String product = input.get("product");
	String price = input.get("product_price");
	
	Product_Page Product_Page = Login_Page.loginApplication(username, password);
	Product_Page.addProductToCart(product);
	Product_Page.verifyCartCount("1");
	Product_Page.goToShoppingCart();
	Product_Page.verifyProductAndPriceDisplayed(product, price);
	Login_Page.goToAllItems();
	Product_Page.verifyProductDetailsDisplay();
	Product_Page.verifySortByOptionsValues();
	Login_Page.logoutApplication();
}
	@DataProvider
	public Object[][] getTestData() throws IOException {
		List<HashMap<String,String>> data = getJsonData(System.getProperty("user.dir")+"\\main\\resources\\saucaLab_TestData\\SauceLab_Single_Purchase_Order_Data.json");
		return new Object[][] {{data.get(0)}};
	}
}