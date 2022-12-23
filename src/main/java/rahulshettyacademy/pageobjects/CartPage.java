package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {
	// Page Object class should not have data it should only focus on elements and
	// actions

	// Local Object variable
	WebDriver driver;

	// PageFactory Design Pattern to reduce the syntax of your WebElements
	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;

	@FindBy(css = ".cartSection h3")
	private List<WebElement> cartProducts;

	// Constructor
	public CartPage(WebDriver driver) {
		// To connect with Parent constructor to pass driver
		super(driver);
		// initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Action Methods
	public Boolean VerifyProductDisplay(String productName) {
		// Check cart section and return true for any of items if it matches from the list (we are using anyMatch)
		Boolean match = cartProducts.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		checkoutEle.click();
		return new CheckoutPage(driver);
	}

}
