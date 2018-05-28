package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

import Utilities.ExtentReport;

public class AccentureHomePage {

	WebDriver driver;

	public AccentureHomePage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "//*[@id='navigation-menu']//*[contains(text(),'About Accenture')]")
	WebElement About;

	@FindBy(xpath = "//*[@id='AboutAccenture']//*[contains(text(),'About Accenture Home')][1]")
	WebElement AboutInner;

	@FindBy(xpath = "//*[@id='block-what-we-do']//div[@class='accordion-body rotis']")
	WebElement AboutText;

	public void AboutDetails() {

		About.click();
		
		ExtentReport.logger.log(LogStatus.PASS, "Successfully clicked on About on Accenture homepage");
		ExtentReport.logger.log(LogStatus.INFO, "Landed on About Page");

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id='AboutAccenture']//*[contains(text(),'About Accenture Home')][1]")));

		AboutInner.click();
		ExtentReport.logger.log(LogStatus.PASS, "Successfully clicked on About Inner Link on Accenture homepage");
		ExtentReport.logger.log(LogStatus.INFO, "Information in About Page - " + AboutText.getText());
		ExtentReport.logger.log(LogStatus.PASS, "Successfully fetched info on About Page on Accenture homepage");
	}

}
