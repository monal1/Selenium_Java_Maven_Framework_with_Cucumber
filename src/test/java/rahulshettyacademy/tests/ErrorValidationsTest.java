package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = {"ErrorHandling"})
	public void LoginErrorValidation() throws IOException {
		// TODO Selenium test

		// Call loginApplication to enter email,password and click on submit and it will
		// give ProductCatalogue object
		// Give wrong email and Password
		landingPage.loginApplication("mn@gmail.com", "sddf");
		System.out.println(landingPage.getErrorMessage());
		//Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
	}

	@Test
	public void ProductErrorValidation() throws IOException {
		// TODO Selenium test

		// Variable
		String productName = "ZARA COAT 3";

		// Call loginApplication to enter email,password and click on submit and it will
		// give ProductCatalogue object
		ProductCatalogue productCatalogue = landingPage.loginApplication("wow@gmail.com", "ZAQ!2wsx");
		// Create ProductCatalogue object -no need now

		// Get product catalog list
		List<WebElement> products = productCatalogue.getProductList();

		// Click add to cart on that selected product
		productCatalogue.addProductToCart(productName);

		// Click on the cart button and it will land on cart page and will return object
		CartPage cartPage = productCatalogue.goToCartPage(); // child class has access to parent class methods
		// Create CartPage object -no need now

		// Verify product is displaying, this will fail
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");

		// Validate -Should remain in Test file and it should not go in page Object file
		Assert.assertFalse(match);

	}
}
