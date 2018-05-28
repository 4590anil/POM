package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.relevantcodes.extentreports.LogStatus;

import Utilities.ExtentReport;

public class BOAHomePage {

	WebDriver driver;

	public BOAHomePage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(id = "NAV_ABOUT_US")
	WebElement About;

	@FindBy(xpath = "//*[@id='siteMapFooter_0']//*[contains(text(),'Our company')]")
	WebElement AboutCompany;

	// @FindBy(xpath="//div[@class='headline-summary']/p")

	@FindBy(xpath = "//*[@id='item0']/div[2]/div/p")
	WebElement AboutText;

	public void AboutDetails() {

		About.click();
		ExtentReport.logger.log(LogStatus.PASS, "Successfully clicked on About on BOA homepage");
		ExtentReport.logger.log(LogStatus.INFO, "Landed on About Page");
		AboutCompany.click();
		ExtentReport.logger.log(LogStatus.PASS, "Successfully clicked on AboutCompany on BOA homepage");
		ExtentReport.logger.log(LogStatus.INFO, "Information in About Page - " + AboutText.getText());

		ExtentReport.logger.log(LogStatus.PASS, "Successfully fetched About text from BOA");

	}

}
