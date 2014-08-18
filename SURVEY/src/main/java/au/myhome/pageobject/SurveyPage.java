package au.myhome.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;


import au.myhome.dataobject.SurveyData;

public class SurveyPage {
	private WebDriver driver;
	public SurveyPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}
	@CacheLookup
	@FindBy(name = "entry.293653978")
	WebElement favColorTextBox;

	@CacheLookup
	@FindBy(name = "entry.456784911")
	WebElement motherNameTextBox;
	
	@CacheLookup
	@FindBy(name = "entry.1246607123")
	WebElement sonNameTextBox;

	@CacheLookup
	@FindBy(name = "submit")
	WebElement submitButton;
	
	public SurveyPage enterFavColor(String favColor){
		favColorTextBox.sendKeys(favColor);
		return this;
	}
	public SurveyPage enterMotherName(String motherName){
		motherNameTextBox.sendKeys(motherName);
		return this;
	}
	public SurveyPage enterSonName(String sonName){
		sonNameTextBox.sendKeys(sonName);
		return this;
	}
	public SubmitDetails submitDetails(){
		submitButton.click();
		return PageFactory.initElements(driver, SubmitDetails.class);
	}
	public SubmitDetails addDetails(SurveyData surveyData){
		Reporter.log("Fav Color : " + surveyData.getFavColor());
		Reporter.log("Mothers Name : " + surveyData.getMotherName());
		Reporter.log("Get Sons Name : " + surveyData.getSonName());
		return enterFavColor(surveyData.getFavColor())
				.enterMotherName(surveyData.getMotherName())
				.enterSonName(surveyData.getSonName()).submitDetails();
		
		
	}
}
