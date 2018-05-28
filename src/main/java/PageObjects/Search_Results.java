package PageObjects;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;

import TestCases.SearchTest;
import Utilities.ExtentReport;

import config.OR;

public class Search_Results {

	WebDriver driver;
	


	public Search_Results(WebDriver driver) {
		this.driver = driver;
	}

	
	
	//@FindBy(xpath="//*[@id='resultStats']")
	@FindBy(xpath= OR.SearchResult)
	WebElement SearchResult;

	//@FindBy(xpath = "//div[@id='rso']//div[@class='rc']/h3/a")
	@FindBy(xpath =OR.TopResult)
	WebElement TopResult;

	public void ShowResult() {

		String result = SearchResult.getText();
		String[] tokens = result.split(" ");
		String totalResults = tokens[1];

		ExtentReport.logger.log(LogStatus.INFO, "Search Result has fetched " + totalResults + " pages.");

	}

	public SapientHomePage NaviagteToSapient() {
		ExtentReport.logger.log(LogStatus.INFO, "Navigating to Sapient home page");

		TopResult.click();

		return PageFactory.initElements(driver, SapientHomePage.class);
	}

	public BOAHomePage NaviagteToBOA() {
		ExtentReport.logger.log(LogStatus.INFO, "Navigating to Bank of America home page");

		TopResult.click();

		return PageFactory.initElements(driver, BOAHomePage.class);
	}

	public AccentureHomePage NaviagteToAccenture() {
		ExtentReport.logger.log(LogStatus.INFO, "Navigating to Accenture home page");

		TopResult.click();

		return PageFactory.initElements(driver, AccentureHomePage.class);
	}

}
