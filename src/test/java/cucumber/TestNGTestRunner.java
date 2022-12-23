package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

// Provide options
@CucumberOptions(features="src/test/java/cucumber", glue="rahulsehttyacademy.stepDefinitions",
monochrome=true, tags= "@Regression", plugin= {"html:target/cucumber.html"} )
public class TestNGTestRunner extends AbstractTestNGCucumberTests{
	
	
}
