package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Selenium test
		
		// Variable
		String productName = "ZARA COAT 3";

		// Now Chrome drive will be downloaded into your local based on your Chrome
		// browser version
		// All the hardwork is done behind the scene
		WebDriverManager.chromedriver().setup();

		// Now you can start by creating the object for your chrome driver
		WebDriver driver = new ChromeDriver();

		// Global level wait timeout
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.manage().window().maximize();

		// Url will you land on the page
		driver.get("https://rahulshettyacademy.com/client");

		// Create object for Landing Page
		LandingPage landingPage = new LandingPage(driver);
		
		// Enter email and password
		driver.findElement(By.id("userEmail")).sendKeys("mon@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("ZAQ!2wsx");

		// Click on login button
		driver.findElement(By.id("login")).click();
		
		// Create wait object
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		// Find all shop elements
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		// Java Streams will help you to iterate on every product items and get the match product
		WebElement prod = products.stream().filter(product -> 
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
		// Click add to cart on that selected product
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		
		// Wait till toast message show up on the screen
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		// ng-animating item 
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		// click on the cart button
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		// Check cart section
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		// Return true for any of items if it matches from the list (we are using anyMatch)
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		
		Assert.assertTrue(match);
		
		
		// Click on checkout button
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));
		
		driver.findElement(By.xpath("(//button[contains(@class, 'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		// VALIDATION
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		// close session
		driver.close();
		driver.quit();

	}

}
