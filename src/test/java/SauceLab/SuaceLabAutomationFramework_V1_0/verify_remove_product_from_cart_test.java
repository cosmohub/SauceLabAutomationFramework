package SauceLab.SuaceLabAutomationFramework_V1_0;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import sauceLab_Base_Pages.Cart_Page;
import sauceLab_Base_Pages.Login_Page;
import sauceLab_Base_Pages.Product_Page;
import sauceLab_Utilities.BaseTest;

public class verify_remove_product_from_cart_test extends BaseTest {
	

	@Test(dataProvider="getTestData")
	public void removeProductFromCart(HashMap<String, String> input) {
		String username = input.get("username");
		String password = input.get("password");
		String product = input.get("product");
		String price = input.get("product_price");
				
		Product_Page ProductPage = Login_Page.loginApplication(username, password);
		ProductPage.verifyProductAndPriceDisplayed(product, price);
		ProductPage.addProductToCart(product);
		ProductPage.verifyCartCount("1");
		Cart_Page Cart_Page = ProductPage.goToShoppingCart();
		ProductPage.verifyProductAndPriceDisplayed(product, price);
		Cart_Page.removeProduct();
		ProductPage.verifyUnavailabilityOfCartCount();
	}
	
	@DataProvider
	public Object[][] getTestData() throws IOException {
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")+"\\main\\resources\\saucaLab_TestData\\SauceLab_Single_Purchase_Order_Data.json");
		return new Object[][] {{data.get(0)}};
	}
}
