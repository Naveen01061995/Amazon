package com.roughWork;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AmazonPractice {

	static {
		System.setProperty("webdriver.gecko.driver", ".//src//test//java//driverExcutables//geckodriver.exe");
	}

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.amazon.com/");
		WebElement findElement = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		findElement.sendKeys("Honor 8x phone");
		driver.findElement(By.xpath("//input[@type='submit' and @value='Go']")).click();

		Thread.sleep(10000);

		List<WebElement> findElements = driver.findElements(By.xpath("//span[contains(text(),'Honor')]"));

		for (int i = 0; i < findElements.size(); i++) {
			System.out.println(findElements.get(i).getText());
		}
	}

}
