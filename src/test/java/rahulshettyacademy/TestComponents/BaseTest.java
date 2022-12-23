package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class BaseTest {

	// Global Variable
	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {

		// Using Properties class from resources GlobalData.properties
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "//src//main//java//rahulshettyacademy//resources//GlobalData.properties");
		prop.load(fis);

		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");
		// String browserName = prop.getProperty("browser");

		if (browserName.contains("chrome")) {

			// Give clue to run in Headless mode using ChromeOption
			ChromeOptions options = new ChromeOptions();

			// Now Chrome drive will be downloaded into your local based on your Chrome
			// browser version
			// All the hardwork is done behind the scene
			WebDriverManager.chromedriver().setup();

			if (browserName.contains("headless")) {
				// Add argument for Headless
				options.addArguments("headless");
			}

			// Now you can start by creating the object for your chrome driver
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900));
			
		} else if (browserName.equalsIgnoreCase("firefox")) {
			// Now Firefox drive will be downloaded into your local based on your Firefox
			WebDriverManager.firefoxdriver().setup();
			// Now you can start by creating the object for your chrome driver
			driver = new FirefoxDriver();
		}

		// Global level wait timeout
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// driver.manage().window().maximize();

		return driver;
	}

	// Generic and it can be used for any test
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// Java: Read Json file and convert it into string also provide what encoding
		// format
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// Convert string into HashMap -Jackson Databind,
		ObjectMapper mapper = new ObjectMapper();
		// We are asking to create HashMap based upon number of indexes we have in that
		// Json file and all HashMap put me in one List
		// and give it back (data) so that List is returned, so data list has 2
		// arguments {map, map}
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		// Cast the driver : (TakesScreenshot)driver So Drive know it has to take a
		// screenshot
		TakesScreenshot ts = (TakesScreenshot) driver;
		// ts has capability of taking photo from the driver
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		// we want to store that file into our local workspace
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

	// Extent Reports

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		// Initialize Driver
		driver = initializeDriver();

		// Create object for Landing Page
		landingPage = new LandingPage(driver);

		// Url to land on the page
		landingPage.goTo();

		return landingPage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}
}
