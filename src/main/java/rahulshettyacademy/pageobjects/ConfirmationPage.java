package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {

	// Local variable
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		// To connect with Parent constructor to pass driver
		super(driver);
		// initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// PageFactory Design Pattern to reduce the syntax of your WebElements
	@FindBy(css = ".hero-primary")
	WebElement confirmationMessage;
	
	// Action Method
	public String getConfirmMessage() {
		return confirmationMessage.getText();
	}
}
