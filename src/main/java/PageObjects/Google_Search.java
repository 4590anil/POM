package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;

import Utilities.ExtentReport;

import config.OR;

public class Google_Search {

	WebDriver driver;

	public Google_Search(WebDriver driver) {
		this.driver = driver;
		}
	
	
	
	//fetchLoactorValue
	//@FindBy(id = "lst-ib")
	@FindBy(id = OR.SearchBox)
	static WebElement SearchBox;

	// public static String CompanyName;

	public Search_Results search(String SearchContent) {
		// this.CompanyName=SearchContent;

		ExtentReport.logger.log(LogStatus.INFO, "Searching for - " + SearchContent);

		SearchBox.sendKeys(SearchContent);
		
		SearchBox.submit();
		


		return PageFactory.initElements(driver, Search_Results.class);
	}

}
