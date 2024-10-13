package SauceLab.SuaceLabAutomationFramework_V1_0;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import sauceLab_Base_Pages.Product_Page;
import sauceLab_Utilities.BaseTest;

public class verify_reset_app_state_test extends BaseTest {
	@Test(dataProvider="getTestData")
	public void resetAppState(HashMap<String, String> input) {
		String username = input.get("username");
		String password = input.get("password");
		String product = input.get("product");
		
		Product_Page Product_Page = Login_Page.loginApplication(username, password);
		Product_Page.addProductToCart(product);
		Product_Page.verifyCartCount("1");
		Login_Page.resetAppState();
		Login_Page.closeMenu();
		Product_Page.verifyUnavailabilityOfCartCount();
		Login_Page.logoutApplication();
		
	}
	
	@DataProvider
	public Object[][] getTestData() throws IOException {
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")+"\\main\\resources\\saucaLab_TestData\\SauceLab_Single_Purchase_Order_Data.json");
		return new Object[][] {{data.get(0)}};
	}
}
