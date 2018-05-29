package PageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import com.relevantcodes.extentreports.LogStatus;

import Utilities.ExtentReport;

public class SapientHomePage {

	WebDriver driver;

	public SapientHomePage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "//md-grid-list[@class='mainNav ng-isolate-scope']//*[@title='ABOUT']/figure/img")
	WebElement About;

	@FindBy(xpath = "//div[@class='PageContainer']//div[@class='titleComp titlecomp']/h2")
	WebElement AboutText;
/*
	public void AboutDetails(ITestResult result) throws Exception {

		try {
			
		About.click();
		ExtentReport.logger.log(LogStatus.PASS, "Successfully clicked on About on Sapient homepage");
		}
		catch (Exception e) {
			ExtentReport.logger.log(LogStatus.FAIL, "Failed on clicking the about tab - "+e);
			String screenshotPath = ExtentReport.getScreenshot(driver, result.getName());
			 //To add it in the extent report 
			ExtentReport.logger.log(LogStatus.FAIL, ExtentReport.logger.addScreenCapture(screenshotPath));
		}
		ExtentReport.logger.log(LogStatus.INFO, "Landed on About Page");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		ExtentReport.logger.log(LogStatus.INFO, "Information in About Page - " + AboutText.getText());
		ExtentReport.logger.log(LogStatus.PASS, "Successfully fetched info from About Page of Sapient");
	}
	*/
	
	public void AboutDetails()  {

		try {
			
		About.click();
		ExtentReport.logger.log(LogStatus.PASS, "Successfully clicked on About on Sapient homepage");
		}
		catch (Exception e) {
			ExtentReport.logger.log(LogStatus.FAIL, "Failed on clicking the about tab - "+e);
	
			 //To add it in the extent report 

		}
		ExtentReport.logger.log(LogStatus.INFO, "Landed on About Page");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		ExtentReport.logger.log(LogStatus.INFO, "Information in About Page - " + AboutText.getText());
		ExtentReport.logger.log(LogStatus.PASS, "Successfully fetched info from About Page of Sapient");
	}
}
