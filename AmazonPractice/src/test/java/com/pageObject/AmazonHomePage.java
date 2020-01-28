package com.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmazonHomePage {

	@FindBy(xpath = "//input[@id='twotabsearchtextbox']")
	WebElement searchInbox;

	@FindBy(xpath = "//input[@type='submit' and @value='Go']")
	WebElement searchInbotton;

	public AmazonHomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void searchProduct(String productName) {
		searchInbox.sendKeys(productName);
	}

	public void clickonSearchButton() {
		searchInbotton.click();
	}

}
