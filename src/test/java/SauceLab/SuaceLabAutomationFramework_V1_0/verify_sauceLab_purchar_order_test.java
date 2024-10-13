package SauceLab.SuaceLabAutomationFramework_V1_0;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import sauceLab_Base_Pages.Cart_Page;
import sauceLab_Base_Pages.Checkout_Overview_Page;
import sauceLab_Base_Pages.Checkout_Page;
import sauceLab_Base_Pages.Order_Finish_Page;
import sauceLab_Base_Pages.Product_Page;
import sauceLab_Utilities.BaseTest;

public class verify_sauceLab_purchar_order_test extends BaseTest{
	
	@Test(dataProvider="getTestData")
	public void purchaseOrder(HashMap<String, String> input) {
		String username = input.get("username");
		String password = input.get("password");
		String product = input.get("product");
		String price = input.get("product_price");
		String firstName = input.get("firstName");
		String lastName = input.get("lastName");
		String postalCode = input.get("postalCode");
		String tax = input.get("tax");
		String totalPrice = input.get("totalPrice");
		
		
		Product_Page ProductPage = Login_Page.loginApplication(username, password);
		ProductPage.verifyProductAndPriceDisplayed(product, price);
		ProductPage.addProductToCart(product);
		ProductPage.verifyCartCount("1");
		Cart_Page Cart_Page = ProductPage.goToShoppingCart();
		ProductPage.verifyProductAndPriceDisplayed(product, price);
		Checkout_Page Checkout_Page = Cart_Page.gotoCheckout();
				
		Checkout_Overview_Page Checkout_Overview_Page = Checkout_Page.complete_checkout_process(firstName, lastName, postalCode);
		ProductPage.verifyProductAndPriceDisplayed(product, price);
		Checkout_Overview_Page.verifyPaymentInfo();
		Checkout_Overview_Page.verifyShippingInfo();
		Checkout_Overview_Page.verifyItemPrice(price);
		Checkout_Overview_Page.verifyTaxTotal(tax);
		Checkout_Overview_Page.verifyItemTotal(totalPrice);
		Order_Finish_Page Order_Finish_Page = Checkout_Overview_Page.finishCheckoutProcess();
		Order_Finish_Page.verifyOrderConfirmation();	
		Login_Page.logoutApplication();
	
	}
	
	@DataProvider
	public Object[][]  getTestData() throws IOException {
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")+"\\main\\resources\\saucaLab_TestData\\SauceLab_Single_Purchase_Order_Data.json");
		return new Object[][] {{data.get(0)}};
	}

}
