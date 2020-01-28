package com.testCases;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.utilities.CaptureScreeenShot;
import com.utilities.PropertiesFileReader;

public class BaseClass implements FilePaths {

	public WebDriver driver = null;
	public static ExtentReports report;
	public static ExtentTest logger;

	static {
		System.setProperty("webdriver.gecko.driver", firefoxPath);
		System.setProperty("webdriver.chrome.driver", chromePath);

	}

	@BeforeSuite
	public void launchBrower() {

		Properties prop = PropertiesFileReader.propertyFileLoad(propertiesfilepath);

		if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();

		} else if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}

		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterSuite
	public void closeBrowser() {
		driver.quit();
	}

	@BeforeMethod
	public void config() {
		report = new ExtentReports(extendReportpath);
		report.loadConfig(new File(extentReportXmlpath));

		logger = report.startTest(this.getClass().getSimpleName()).assignCategory("Amzon");

		// Test Case Usage: Using it at Every Step in Each Test Case
		logger.log(LogStatus.INFO, this.getClass().getName() + " is excuting");
	}

	// @AfterMethod
	@AfterMethod(alwaysRun = true)
	public void TearDown_AM(ITestResult result) throws IOException {
		System.out.println("@After Method");
		try {
			if (result.getStatus() == ITestResult.FAILURE) {
				String res = CaptureScreeenShot.captureScreenShot(driver, result.getName());
				String image = logger.addScreenCapture(res);
				System.out.println(image);
				String TestCaseName = this.getClass().getSimpleName() + " Test Case Failure";
				logger.log(LogStatus.FAIL, TestCaseName + logger.addScreenCapture(res));
				logger.log(LogStatus.FAIL, image, this.getClass().getSimpleName() + " TestCase Failure ");
			} else if (result.getStatus() == ITestResult.SUCCESS) {

				logger.log(LogStatus.PASS, this.getClass().getSimpleName()
						+ " Test Case Success all products has been printented on the console");
			} else if (result.getStatus() == ITestResult.SKIP) {
				logger.log(LogStatus.SKIP, this.getClass().getSimpleName() + " Test Case Skipped");
			}
			report.endTest(logger);
			report.flush();

		} catch (Throwable t) {
			logger.log(LogStatus.ERROR, t.fillInStackTrace());
		}

	}
}
