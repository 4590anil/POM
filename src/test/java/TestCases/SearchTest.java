package TestCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import PageObjects.Google_Search;
import PageObjects.Search_Results;
import Utilities.ExcelFileReader;
import Utilities.ExtentReport;

import org.testng.ITestResult;

public class SearchTest {

	WebDriver driver;
	String DriverPath;
	ITestResult result;
	Properties conf;


	@BeforeSuite
	public void setup() {
		// DriverPath = System.getProperty(("user.dir")+"\\src\\main\\java\\Drivers");
		ExtentReport.startReport();

	}

	@Parameters("browser")
	@BeforeTest
	public void driverSetup(@Optional("Chrome") String browser) {
	

		if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver",	"C:\\Users\\anirawat1\\Downloads\\chromedriver_win32\\chromedriver.exe");
			//final File file = new File("Drivers/chromedriver.exe");
			//System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
			//System.out.println(file.getAbsolutePath());
			//System.setProperty("webdriver.chrome.driver","C:\\Users\\anirawat1\\Downloads\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
			ExtentReport.startTest("Testing in Chrome");
			ExtentReport.logger.log(LogStatus.INFO, "Test Case execution started in Chrome browser");
		} else {
			// System.setProperty("webdriver.ie.driver", "DriverPath\\IEDriverServer.exe");

			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();

			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

			System.setProperty("webdriver.ie.driver",
					"C:\\Users\\anirawat1\\Downloads\\IEDriverServer_x64_3.12.0\\IEDriverServer.exe");
			driver = new InternetExplorerDriver(capabilities);
			ExtentReport.startTest("Testing in IE");
			ExtentReport.logger.log(LogStatus.INFO, "Test Case execution started in IE browser");
		}
		driver.manage().window().maximize();
		
		
	}

	@BeforeMethod
	public void launchBrowser() {
	conf = new Properties();

		try {
			// System.out.println(System.getProperty("user.dir")+"\\src\\main\\java\\config\\config.properties");
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\config\\config.properties");
			conf.load(fis);
		} catch (IOException IO) {
			IO.printStackTrace();
		}

		driver.get(conf.getProperty("testURL"));

		ExtentReport.logger.log(LogStatus.INFO, "Test URL - " + conf.getProperty("testURL"));

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test(dataProvider = "testData")
	public void Search(String SearchContent) throws Exception {

		Google_Search gs = PageFactory.initElements(driver, Google_Search.class);
		// gs.search(SearchContent).ShowResult();

		Search_Results sr = gs.search(SearchContent);

		sr.ShowResult();

		if (SearchContent.equals("Sapient")) {
			sr.NaviagteToSapient().AboutDetails();
		} else if (SearchContent.equals("Accenture")) {
			sr.NaviagteToAccenture().AboutDetails();
		} else {
			sr.NaviagteToBOA().AboutDetails();
		}

	}

	@DataProvider(name = "testData")
	public Object[][] TestData() throws IOException {
		String FilePath = System.getProperty("user.dir") + "\\src\\main\\java\\TestData\\SearchTestData.xlsx";
		String SheetName = "TestInput";
		Object[][] SearchInputFields = ExcelFileReader.getDataFromExcel(FilePath, SheetName);
		return SearchInputFields;
	}

	@AfterMethod
	public void testclosure(ITestResult result) throws Exception {
		
		if (result.getStatus() == ITestResult.FAILURE)
		{


			ExtentReport.logger.log(LogStatus.FAIL, "Test Case Failed in " + result.getName());
			ExtentReport.logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
			String screenshotPath = ExtentReport.getScreenshot(driver, result.getName());
			ExtentReport.logger.log(LogStatus.FAIL, ExtentReport.logger.addScreenCapture(screenshotPath));
			System.out.println(screenshotPath);
		}
			
		else if (result.getStatus() == ITestResult.SKIP)
			ExtentReport.logger.log(LogStatus.SKIP, "Test Case Skipped in " + result.getName());
		else {
			ExtentReport.logger.log(LogStatus.PASS, "Test Case Passed in " + result.getName());
		}
		ExtentReport.endTest();

		// ExtentReport.logger.log(LogStatus.PASS, "Test Passed");

	}
	
	@AfterTest
	public void closeBrowser() {
		driver.close();
	}

	@AfterSuite
	public void teardown() {

		// ExtentReport.endTest();
			ExtentReport.endReport();
			driver.quit();
	}
}
