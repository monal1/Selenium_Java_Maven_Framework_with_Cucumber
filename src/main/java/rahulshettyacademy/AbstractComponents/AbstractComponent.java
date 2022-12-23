package rahulshettyacademy.AbstractComponents;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.OrderPage;

public class AbstractComponent {

	// Local variable
	WebDriver driver;

	// Constructor
	public AbstractComponent(WebDriver driver) {
		// connecting local drive with the driver coming from test page
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// PageFactory Code
	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;

	public CartPage goToCartPage() {
		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage goToOrderPage() {
		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}

	// Create wait object for element to appear
	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}

	// Create wait method for invisibility
	public void waitForElementToDisappear(WebElement ele) {
		// 4 seconds -Application
		// Thread.sleep(1000); Or do this
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		// ng-animating item
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

}
