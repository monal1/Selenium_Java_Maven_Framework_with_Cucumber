package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
	// Page Object class should not have data it should only focus on elements and
	// actions

	// Local Object variable
	WebDriver driver;

	// PageFactory Design Pattern to reduce the syntax of your WebElements
	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> productNames;

	// Constructor
	public OrderPage(WebDriver driver) {
		// To connect with Parent constructor to pass driver
		super(driver);
		// initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Action Methods
	public Boolean VerifyOrderDisplay(String productName) {
		// Check cart section and return true for any of items if it matches from the list (we are using anyMatch)
		Boolean match = productNames.stream().anyMatch(product->product.getText().equalsIgnoreCase(productName));
		return match;
	}

}
