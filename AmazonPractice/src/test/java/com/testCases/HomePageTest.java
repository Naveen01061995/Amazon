package com.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.pageObject.AmazonHomePage;
import com.pageObject.ProductPage;

public class HomePageTest extends BaseClass {

	@Test
	public void searchProduct() {
		AmazonHomePage page = new AmazonHomePage(driver);
		page.searchProduct("Honor");
		page.clickonSearchButton();
		ProductPage productpage = new ProductPage(driver);
		productpage.printAllProducts();
	}

}
