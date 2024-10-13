package SauceLab.SuaceLabAutomationFramework_V1_0;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import sauceLab_Base_Pages.Product_Page;
import sauceLab_Utilities.BaseTest;

public class Verify_different_sauce_lab_logins extends BaseTest{
	
	@Test(dataProvider="getTestData")
	public void purchaseOrder(HashMap<String, String> input) {
		String username = input.get("username");
		String password = input.get("password");
					
		Product_Page ProductPage = Login_Page.loginApplication(username, password);
		if (username!="locked_out_user") 
		{
		ProductPage.verifyProductDetailsDisplay();
		ProductPage.verifySortByOptionsValues();
		Login_Page.logoutApplication();
		}
		else if (username=="locked_out_user")
		{		
		Login_Page.verifyLoginError();	
		}
	}
		@DataProvider
		public Object[][]  getTestData() throws IOException {
			List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")+"\\main\\resources\\saucaLab_TestData\\SuaceLab_Different_User_Logins.json");
			return new Object[][] {{data.get(0)},{data.get(1)},{data.get(2)},{data.get(3)}};
		}
		
}
