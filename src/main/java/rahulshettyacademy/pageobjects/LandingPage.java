package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	// Page Object class should not have data it should only focus on elements and
	// actions

	// Local Object variable
	WebDriver driver;

	// Constructor first thing to execute
	public LandingPage(WebDriver driver) {
		// To connect with Parent constructor to pass driver
		super(driver);
		// initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// No more needed using PageFactory: WebElement userEmail =
	// driver.findElement(By.id("userEmail"));
	// PageFactory Design Pattern to reduce the syntax of your WebElements
	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement passwordEle;

	@FindBy(id = "login")
	WebElement submit;
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;

	// Action Methods
	public ProductCatalogue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		// Land on new page ProductCatalogue
		// Create ProductCatalogue object
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;

	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
}
