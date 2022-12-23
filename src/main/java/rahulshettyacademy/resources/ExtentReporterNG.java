package rahulshettyacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getReportObject() {
		// All configuration for Extent Report
		// 2 Classes to for Extent Report: ExtentReports, ExtentSparkReporter
		// First we create object of these 2 classes
		// Later using these 2 classes together in building the report
		String path = System.getProperty("user.dir") + "//reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);

		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");

		// Main class to driver all reporting execution
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Montest");
		return extent; // This is what we need in Listeners.java
	}
}
