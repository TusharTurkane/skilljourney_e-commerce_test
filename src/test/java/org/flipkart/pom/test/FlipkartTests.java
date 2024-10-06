package org.flipkart.pom.test;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Object.utilities.AddtoCartPage;
import Object.utilities.FiltersPage;
import Object.utilities.FlipkartHomePage;
import Object.utilities.ProductDetailPage;
import Object.utilities.SearchResultPage;
import Object.utilities.ProductDetailPage;

public class FlipkartTests extends TestBase {

	FlipkartHomePage fHomePage;
	SearchResultPage sResultPage;
	FiltersPage filterPage;
	ProductDetailPage productPage;
	AddtoCartPage cartPage;

	@BeforeClass
	void initializeObjects() {
		fHomePage = new FlipkartHomePage(driver);
		sResultPage = new SearchResultPage(driver);
		filterPage = new FiltersPage(driver);
		productPage = new ProductDetailPage(driver);
		cartPage = new AddtoCartPage(driver);
	}

	@BeforeTest
	public void OpenApplication() throws IOException {
		initializeDriver();
		driver.navigate().to("https://www.flipkart.com/");
	}

	@AfterMethod
	public void NavigatetoHomePage() {
		driver.navigate().to("https://www.flipkart.com/");
		fHomePage.closeLoginPopUp();

	}

	@Test(enabled = false)
	void testloginPopUpClose() throws IOException {

		// If login popup opens, close it
		fHomePage.closeLoginPopUp();

	}

	@Test(priority = 4)
	void testSearchForProduct() {

		// Set Product Name you wish to search
		String searchProductName = "phone";

		// search Product on Flipkart
		fHomePage.enterProductInSearchBar(searchProductName);
		fHomePage.hitEnterButtonAfterSearchText();

		// Print the total number of results found
		int totalNumberOfResult = sResultPage.getCountforSearchResults();
		// System.out.println("total number of results found : " + totalNumberOfResult);

		// Assert that search results are displayed with the correct item
		String actualProductName = sResultPage.verifySearchResults();
		Assert.assertEquals(actualProductName, searchProductName);
	}

	@Test(priority = 2)
	void testSearchBarSuggestions() {

		// Enter Product in Search bar
		String searchProduct = "iphone 16";
		fHomePage.enterProductInSearchBar(searchProduct);

		// Verify that the Suggestions box opens
		Boolean isAutoSuggestionTextPresent = fHomePage.verifyAutoSuggestionText(searchProduct);
		// System.out.println(isAutoSuggestionTextPresent);

		// Verify that the Suggestions box contains the product in each of the search string
		Assert.assertTrue(isAutoSuggestionTextPresent);

		// Verify that the suggestions box contains 8 results
		int expectedSuggestionCount = 8;
		int actualSuggestionCount = fHomePage.verifyAutoSuggestionCount();
		Assert.assertEquals(actualSuggestionCount, expectedSuggestionCount);

	}

	@Test(priority = 3)
	void testFiltersProductSearch() {

		// Set Product Name you wish to search
		String searchProductName = "phone";

		// search Product on Flipkart
		fHomePage.enterProductInSearchBar(searchProductName);
		fHomePage.hitEnterButtonAfterSearchText();

		// apply brand Filter
		filterPage.applyBrandFilter("SAMSUNG");

		// apply price Filter
		filterPage.applyPriceFilter("15000", "20000");

		// apply discount Filter
		filterPage.applyDiscountFilter();

		// Exclude Out of stock Filter
		filterPage.applyAvialiblityFilter();

		// Print the number of results found after all the filters are applied
		int NumberofResultDisplayed = filterPage.verifyNumberofResultDisplayed();
		System.out.println("number of Results Found After all the Filters Applied " + NumberofResultDisplayed);

	}

	@Test(priority = 5)
	void testProductDetailAndAddToCart() throws InterruptedException {
		// Set Product Name you wish to search
		String searchProductName = "phone";

		// search Product on Flipkart
		fHomePage.enterProductInSearchBar(searchProductName);
		fHomePage.hitEnterButtonAfterSearchText();

		// Click on the product result with the lowest discount percentage. Capture the
		// price displayed
		sResultPage.clickResultWithHighestDiscount();

		sResultPage.switchTab();

		productPage.addToCart();

		int totalAmountProductPag = productPage.getTotalAmountProductPage();

		cartPage.verifyPageFields();

		int totalAmountonCartPage = cartPage.getTotalAmountCartPage();

		Assert.assertEquals(totalAmountonCartPage, totalAmountProductPag);
	}

	@Test(dataProvider = "getBasicFormDataFromExcelFile" , priority = 1)
	void testflipkartDataDriven(String productName, String brandName, String minPrice, String maxPrice) {

		fHomePage.enterProductInSearchBar(productName);
		fHomePage.hitEnterButtonAfterSearchText();

		filterPage.applyBrandFilter(brandName);

		filterPage.applyPriceFilter(minPrice, maxPrice);

		filterPage.applyDiscountFilter();

		filterPage.applyAvialiblityFilter();
		

	}
	
	

}
