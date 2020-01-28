package com.utilities;

import java.io.File;
import java.io.IOException;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;



public class CaptureScreeenShot {

	public static String captureScreenShot(WebDriver driver,String methodName) {

		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
		return screenshotAs.toString();
		
	}

}
