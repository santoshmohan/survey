package au.myhome.test;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import au.myhome.core.SelTestCase;
import au.myhome.dataobject.SurveyData;
import au.myhome.pageobject.SurveyPage;

public class WebDriverTest extends SelTestCase{


	@Test(dataProvider = "surveyData", dataProviderClass = SurveyData.class )
	public void testSurvey(SurveyData SuveryData){
		driver.get("https://docs.google.com/forms/d/1_Dqldp0BTVNUI5LHEw1CuZY5zR-eeuEUUvOGvnLjERg/viewform?c=0&w=1&usp=mail_form_link");		
		//driver.get(appURL);
		SurveyPage survey = PageFactory.initElements(driver, SurveyPage.class);
		survey.addDetails(SuveryData);
		assert driver.findElement(By.tagName("body")).getText().contains("Your response has been recorded.");
		Reporter.log("Test Passed");		
		
	}
	@Test(dataProvider = "surveyCSVData", dataProviderClass= SurveyData.class)
	public void newSurveyUsingCSVFile(SurveyData SuveryData){
		driver.get("https://docs.google.com/forms/d/1_Dqldp0BTVNUI5LHEw1CuZY5zR-eeuEUUvOGvnLjERg/viewform?c=0&w=1&usp=mail_form_link");		
		//driver.get(appURL);
		SurveyPage survey = PageFactory.initElements(driver, SurveyPage.class);
		survey.addDetails(SuveryData);
		assert driver.findElement(By.tagName("body"))
					 .getText()
					 .contains("Your response has been recorded.");
		Reporter.log("Test Passed");
	}
}
