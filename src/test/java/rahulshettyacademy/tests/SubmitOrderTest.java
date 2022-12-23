package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	// Global Class Variable
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException {
		// TODO Selenium test

		// Call loginApplication to enter email,password and click on submit and it will
		// give ProductCatalogue object
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		// Create ProductCatalogue object -no need now

		// Get product catalog list
		List<WebElement> products = productCatalogue.getProductList();

		// Click add to cart on that selected product
		productCatalogue.addProductToCart(input.get("productName"));

		// Click on the cart button and it will land on cart page and will return object
		CartPage cartPage = productCatalogue.goToCartPage(); // child class has access to parent class methods
		// Create CartPage object -no need now

		// Verify product is displaying
		Boolean match = cartPage.VerifyProductDisplay(input.get("productName"));

		// Validate -Should remain in Test file and it should not go in page Object file
		Assert.assertTrue(match);

		// Go to checkout
		CheckoutPage checkoutPage = cartPage.goToCheckout();

		// Select country
		checkoutPage.selectCountry("india");

		// Submit order
		ConfirmationPage confirmationPage = checkoutPage.sumbitOrder();

		// VALIDATION
		String confirmMessage = confirmationPage.getConfirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		// To verify ZARA COAT 3 is displaying in orders page

		ProductCatalogue productCatalogue = landingPage.loginApplication("mon@gmail.com", "ZAQ!2wsx");
		OrderPage ordersPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}

	// Data Provider with HashMap & Json files -run test with 2 data sets
	@DataProvider
	public Object[][] getData() throws IOException {
		// List of HashMap is return
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//main//java//rahulshettyacademy//data/PurchaseOrder.json");
		// two Dimensional Array
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

	/*
	 * Data Provider with HashMap -run test with 2 data sets
	 * 
	 * @DataProvider public Object[][] getData() {
	 * 
	 * // Create Object for HashMap HashMap<String, String> map = new
	 * HashMap<String, String>(); map.put("email", "mon@gmail.com");
	 * map.put("password", "ZAQ!2wsx"); map.put("productName", "ZARA COAT 3");
	 * 
	 * HashMap<String, String> map1 = new HashMap<String, String>();
	 * map1.put("email", "wow@gmail.com"); map1.put("password", "ZAQ!2wsx");
	 * map1.put("productName", "ADIDAS ORIGINAL");
	 * 
	 * // two Dimensional Array return new Object[][] { { map }, { map1 } }; }
	 */

	/*
	 * Data Provider 2DArray run test with 2 data sets
	 * 
	 * @DataProvider public Object[][] getData() { // two Dimensional Array return
	 * new Object[][] { { "mon@gmail.com", "ZAQ!2wsx", "ZARA COAT 3" }, {
	 * "wow@gmail.com", "ZAQ!2wsx", "ADIDAS ORIGINAL" } }; }
	 */

}
