package com.roughWork;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.utilities.CaptureScreeenShot;

public class Reports11 {

	public static ExtentReports report;
	public static ExtentTest logger;
	public static WebDriver driver;

	public void config(String path) {
		report = new ExtentReports(path);
		report.loadConfig(new File(".\\src\\test\\java\\extendreport\\Extentreport.xml"));

		logger = report.startTest(this.getClass().getSimpleName()).assignCategory("Happy Path");

		// Test Case Usage: Using it at Every Step in Each Test Case
		logger.log(LogStatus.INFO, "String Message to Log for Each Step in Test Case");
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
				String TestCaseName = this.getClass().getSimpleName()
						+ " Test Case Failure and Title/Boolean Value Failed";
				logger.log(LogStatus.FAIL, TestCaseName + logger.addScreenCapture(res));
				// logger.log(LogStatus.FAIL, image, this.getClass().getSimpleName() + " Test
				// Case Failure and Title/Boolean Value Failed");
			} else if (result.getStatus() == ITestResult.SUCCESS) {
				logger.log(LogStatus.PASS, this.getClass().getSimpleName() + " Test Case Success and Title Verified");
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
