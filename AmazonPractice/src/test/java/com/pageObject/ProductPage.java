package com.pageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

	@FindBy(xpath = "//span[@class='a-size-base-plus a-color-base a-text-normal']")
	public List<WebElement> allProducts;

	public ProductPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void printAllProducts() {

		for (int i = 0; i < allProducts.size(); i++) {
			System.out.println(allProducts.get(i).getText());
		}
	}
}
