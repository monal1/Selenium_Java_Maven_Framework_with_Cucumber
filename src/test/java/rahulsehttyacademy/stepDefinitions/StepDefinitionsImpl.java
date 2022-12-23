package rahulsehttyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionsImpl extends BaseTest {

	// Global object
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		// Code to land on Ecommerce
		landingPage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String email, String password) {
		// Call loginApplication to enter email,password and click on submit and it will
		// give ProductCatalogue object
		productCatalogue = landingPage.loginApplication(email, password);
	}

	@When("^I add product (.+) to Cart$")
	public void I_add_product_to_Cart(String productName) {
		// Get product catalog list
		List<WebElement> products = productCatalogue.getProductList();

		// Click add to cart on that selected product
		productCatalogue.addProductToCart(productName);
	}

	@And("^Checkout (.+) and submit the order$")
	public void Checkout_submit_the_order(String productName) {
		// Click on the cart button and it will land on cart page and will return object
		CartPage cartPage = productCatalogue.goToCartPage(); // child class has access to parent class methods
		// Create CartPage object -no need now

		// Verify product is displaying
		Boolean match = cartPage.VerifyProductDisplay(productName);

		// Validate -Should remain in Test file and it should not go in page Object file
		Assert.assertTrue(match);

		// Go to checkout
		CheckoutPage checkoutPage = cartPage.goToCheckout();

		// Select country
		checkoutPage.selectCountry("india");

		// Submit order
		confirmationPage = checkoutPage.sumbitOrder();
	}

	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmationPage(String string) {
		// VALIDATION
		String confirmMessage = confirmationPage.getConfirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	   @Then("^\"([^\"]*)\" message is displayed$")
	    public void something_message_is_displayed(String strArg1) {
		   Assert.assertEquals(strArg1, landingPage.getErrorMessage());
		   driver.close();
	    }

}
