package au.myhome.dataobject;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.testng.annotations.DataProvider;

import au.com.bytecode.opencsv.CSVReader;

public class SurveyData {
	private String favColor;
	private String motherName;
	private String sonName;

	private Properties properties = new Properties();
	public SurveyData() throws IOException{
		properties.load(SurveyData.class.getResourceAsStream("/surveydata.properties"));
		favColor = properties.getProperty("favcolor");
		motherName = properties.getProperty("mothername");
		sonName = properties.getProperty("sonname");
	}
	public String getFavColor() {
		// TODO Auto-generated method stub
		return favColor;
	}
	public void setFavColor(String favColor){
		this.favColor = favColor;
	}
	public String getMotherName() {
		// TODO Auto-generated method stub
		return motherName;
	}
	public void setMotherName(String motherName){
		this.motherName = motherName;
	}
	public String getSonName() {
		// TODO Auto-generated method stub
		return sonName;
	}
	public void setSonName(String sonName){
		this.sonName = sonName;
	}
	@DataProvider(name="surveyData")
	public static Object[][] getSurveyData() throws IOException{
	
		
		SurveyData surveyData2 = new SurveyData();
		surveyData2.setFavColor("Green");
		surveyData2.setMotherName("Barkly");
		surveyData2.setSonName("Asleyee");
		return new SurveyData[][]{
				{surveyData2}
				
		};
		
	}
	/**
	 * Test data provider reading data from csv file
	 * @return
	 * @throws IOException
	 */
	@DataProvider(name="surveyCSVData")
	public static Object[][] getCSVData() throws IOException{
	
		CSVReader csvReader = new CSVReader( new FileReader
									(SurveyData.class.getResource("/surveydata.csv").getPath()));
		List<String[]>dataList = csvReader.readAll();
		Object[][]data = new Object[dataList.size()][1];
		List<SurveyData> regList = new ArrayList<SurveyData>();
		for (String[] strArray:dataList){
			SurveyData SurveyData = new SurveyData();
			SurveyData.setFavColor(strArray[0].trim());
			SurveyData.setMotherName(strArray[1].trim());
			SurveyData.setSonName(strArray[2].trim());
			regList.add(SurveyData);
		}
		for (int i=0; i<data.length; i++){
			for (int j=0; j<data[i].length;j++){
				data[i][j] = regList.get(i);
			}
		}
		csvReader.close();
		return data;
	}
}
