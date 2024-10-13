package sauceLab_Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import sauceLab_Base_Pages.Login_Page;


public class BaseTest {
	public WebDriver driver;
	public Login_Page Login_Page;
	Properties prop = new Properties();
	
	public void _initialize_driver() throws IOException{
		FileInputStream filePath = new FileInputStream(System.getProperty("user.dir")+"\\main\\resources\\saucaLab_TestData\\GlobalData.properties");
		prop.load(filePath);
		String browser = System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");
		
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		
		else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		
		else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			EdgeOptions opt = new EdgeOptions();
			opt.addArguments("--guest");			
			driver = new EdgeDriver(opt);
		}
		
	}
	
	@BeforeMethod
	public Login_Page launchApplication() throws IOException {
		_initialize_driver();
		String url = prop.getProperty("url");
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));	
		Login_Page = new Login_Page(driver);
		return Login_Page;
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	public List<HashMap<String, String>> getJsonData(String file) throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(file),StandardCharsets.UTF_8);
		ObjectMapper map = new ObjectMapper();
		List<HashMap<String, String>> data = map.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});		
		return data;
	}
	
	public String takeScreenshot(String fileName) throws IOException {
		String date = new SimpleDateFormat("dd-MM-yyyy_hms").format(new Date());
		String path = System.getProperty("user.dir")+"\\screenshots"+fileName+date+".png";
		TakesScreenshot ts = (TakesScreenshot) driver;
		File Source = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(path);
		FileUtils.copyFile(Source, dest);
		return path;
				
		
	}

}
