package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	// Page Object class should not have data it should only focus on elements and actions

	// Local Object variable
	WebDriver driver;

	// Constructor first thing to execute
	public ProductCatalogue(WebDriver driver) {
		// To connect with Parent constructor to pass driver
		super(driver);
		// initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// PageFactory Design Pattern to reduce the syntax of your WebElements
	// List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css = ".mb-3")
	List<WebElement> products;

	@FindBy(css = ".ng-animating")
	WebElement spinner;

	// Here you can NOT use PageFactory is exclusively for a driver.findElement
	// construction
	// In this case you just have By element
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");

	// Action Methods
	public List<WebElement> getProductList() {
		// This will wait for element to appear
		waitForElementToAppear(productsBy);
		// This will get the product list
		return products;
	}

	public WebElement getProductByName(String productName) {
		// Java Streams will help you to iterate on every product items and get the
		// match product
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
	}

	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		// To confirm element is added to the cart
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
	}

}
