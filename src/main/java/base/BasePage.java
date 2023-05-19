package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BasePage {
	/*
	 * It is the main class which take cares of Browser Setup, loading configuration
	 * files and other reusable methods like screenshot capturing, external data
	 * handling, etc. Using base class we can avoid code duplication. All the test
	 * classes and page objects extends base class.
	 */

	/*
	 * Adding dependencies in pom.xml file Navigate to https://mvnrepository.com/
	 * and search for selenium Click on Selenium Java Click on latest version and
	 * copy the line of codes for dependency Paste it inside dependencies tag in
	 * pom.xml file
	 */

	/*
	 * .properties files are mainly used in java programs to maintain some
	 * configuration data of project. Each parameter in properties file is stored as
	 * key value pair, where each key is stored in new line. If any information is
	 * changed from the properties (chrome to firefox), you dont need to recompile
	 * java classes.
	 */
	public static WebDriver driver;
	public static Properties prop;
	public static ExtentTest test;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;

	public BasePage() {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(
					"C:\\Users\\Lenovo\\eclipse-workspace\\TestNgFramework_MasterX\\src\\main\\java\\config\\config.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			System.out.println(e.getStackTrace());
		} catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
	}

	public static void launchBrowser() {
		String browserName = prop.getProperty("browser");
		// The above line is to read the value of a key passed from properties file.

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\Lenovo\\Downloads\\chromedriver_win32 (7)\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\Lenovo\\Downloads\\geckodriver-v0.32.2-win64 (1)\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else {
			System.out.println("Specified browser is not found.");
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.get(prop.getProperty("url"));
	}

	@BeforeTest
	public void startReport() {
		htmlReporter = new ExtentHtmlReporter(
				"C:\\Users\\Lenovo\\eclipse-workspace\\TestNgFramework_MasterX\\ExtentReport\\extentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		htmlReporter.config().setDocumentTitle("Test Automation Report");
		htmlReporter.config().setReportName("MasterX Report");
	}

	// ITestResult is used to capture the result of TestNg test in extent report

	@AfterMethod
	public void getResult(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getThrowable());
//			String methodName = result.getMethod().getMethodName();
//			String screenshotName = methodName + ".png";
//			String screenshotPath = "C:\\Users\\Lenovo\\eclipse-workspace\\TestNgFramework_MasterX\\ExtentReport" + File.separator + screenshotName;
//
//			// Capture and save the screenshot
//			captureScreenshots(screenshotName); // Implement the logic to capture the screenshot
//
//			// Add the screenshot to the Extent Report
//			try {
//				test.fail(result.getThrowable().getMessage(),
//						MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
//			} catch (IOException e) {
//				test.fail("Failed to attach screenshot");
//			}
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getTestName());
		} else {
			test.log(Status.SKIP, result.getTestName());
		}
	}

	public static String captureScreenshots(String screenShotName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		// TakeScreenshot is an interface which is used to take the screenshots in test automation
		File source = ts.getScreenshotAs(OutputType.FILE);
		// OutputType.FILE -> Storing a screenshot at temporary file
		String dest = "C:\\Users\\Lenovo\\eclipse-workspace\\TestNgFramework_MasterX\\ExtentReport\\" + screenShotName
				+ ".png";
		//We have to store the screenshot in permanent location
		File destination = new File (dest);
		
		FileUtils.copyFile(source, destination);
		//We are copying the file from temporary location (source) to permanent location (destination)
		return dest;

	}

	@AfterTest
	public void closeReport() {
		extent.flush();
	}

}
