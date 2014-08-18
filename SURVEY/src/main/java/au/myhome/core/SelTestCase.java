package au.myhome.core;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class SelTestCase {
	protected WebDriver driver;
	protected String appURL;
	
	//@Parameters("appURL")
	public void setEnv(){
	//		@Optional("https://docs.google.com/forms/d/1_Dqldp0BTVNUI5LHEw1CuZY5zR-eeuEUUvOGvnLjERg/viewform?c=0&w=1&usp=mail_form_link") String appURL){
	//public void setEnv(){
		this.appURL =
				"https://docs.google.com/forms/d/1_Dqldp0BTVNUI5LHEw1CuZY5zR-eeuEUUvOGvnLjERg/viewform?c=0&w=1&usp=mail_form_link";
	}
	
	@Parameters("browser")
	@BeforeMethod()
	public void launchBroswer(@Optional("FF") String browser){
		if(browser.equalsIgnoreCase("CC")){
			System.setProperty("webdriver.chrome.driver", "/Users/santoshmohan/eclipse/chromedriver");
			driver = new ChromeDriver();
		}else if(browser.equalsIgnoreCase("FF")){
			driver = new FirefoxDriver();
		}else{
			driver = new InternetExplorerDriver();
		}
	}

	@AfterMethod
	public void closeBroswer(ITestResult result) throws IOException{
		if (!result.isSuccess()){
			File imageFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String failureImageFileName = result.getMethod().getMethodName()+ new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime());
			File failureImageFile = new File(failureImageFileName);
			FileUtils.moveFile(imageFile, failureImageFile);
		}
		driver.close();
		driver.quit();
	}
}
