package com.hellofresh.challenge;


import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.Date;

/**
 * Test Methods class
 */

public class WebTest {
	WebDriver driver;
	WebDriverWait wait;
	LoggerInfo logger;
	String browserRun;
	SigninPage signinPage;
	CheckoutPage checkoutPage;
	String existingUserEmail = "hf_challenge_123456@hf123456.com";
	String existingUserPassword = "12345678";
	WebTestHelper helper = new WebTestHelper();
	
	/**
	 * <pre>
	 * Intializing Test Method Before Every Run
	 * </pre>
	 * <p>
	 * <pre>
	 *     Steps:
	 *     1. Initializing LoggerInfo
	 *     2. Checking OS
	 *     3. Checking in which browser to run
	 *     
	 */

	@BeforeMethod
	@Parameters("browser")
	public void setup(String browser) throws Exception{

		logger = LoggerInfo.getInstance();

		//Checking OS of System running in different OS - Win & MAC
		logger.Info("OS -"+ helper.getOS());

		//Added 2 browsers for Cross-Browser Testing but can be added as many number of browsers
		//Check if parameter passed from TestNG is 'firefox' and OS is MAC
		if(browser.equalsIgnoreCase("Firefox") && helper.getOS().contains("mac")){
			System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
			driver = new FirefoxDriver();
			browserRun = "Firefox";
		}
		
		//Check if parameter passed as 'Chrome' and OS is MAC
		else if(browser.equalsIgnoreCase("Chrome")&& helper.getOS().contains("mac")){		
			System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
			driver = new ChromeDriver();
			browserRun = "Chrome";
			
		//Check if parameter passed as 'Chrome' and OS is Windows
		}else if(browser.equalsIgnoreCase("Chrome")&& helper.getOS().contains("win")){		
			System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
			driver = new ChromeDriver();
			browserRun = "Chrome";
			
		//Check if parameter passed from TestNG is 'firefox' and OS is Windows
		}else if(browser.equalsIgnoreCase("Firefox") && helper.getOS().contains("win")){
			System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
			driver = new FirefoxDriver();
			browserRun = "Firefox";
		}
		else{
			//If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
		wait = new WebDriverWait(driver, 10, 50);
		driver.get(TestConstants.WEBSITE_URL);

		//Initializing PageFactory
		signinPage = PageFactory.initElements(driver, SigninPage.class);
		checkoutPage = PageFactory.initElements(driver, CheckoutPage.class);
	}
	
	/**
	 * <pre>
	 * Signing in new user
	 * </pre>
	 * <p>
	 * <pre>
	 * Test:Signing in new user
	 *     Steps:
	 *     1. Initializing SignIn PageFactory
	 *     2. Generating Random Values
	 *     3. Setting up data on page using WebDriverUtility function
	 *     4. Validating Assertions    
	 */

	@Test(priority=1)
	public void signInTest() {

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login"))).click();
		String timestamp = String.valueOf(new Date().getTime());

		//Adding Logs
		logger.Info("Timestamp - "+ timestamp);

		String email = "hf_challenge_" + timestamp + "@hf" + timestamp.substring(7) + ".com";
		//Generating Random Name
		String name = helper.generateRandomName();
		String surname = helper.generateRandomName();
		signinPage.email.sendKeys(email);
		signinPage.submitButton.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_gender2"))).click();
		signinPage.firstName.sendKeys(name);
		signinPage.lastName.sendKeys(surname);
		signinPage.password.sendKeys("Qwerty");
		//Selecting values
		WebPageUtils.selectByValue(signinPage.days, "1");
		WebPageUtils.selectByValue(signinPage.months, "1");
		WebPageUtils.selectByValue(signinPage.years, "2000");
		signinPage.company.sendKeys("Company");
		signinPage.address1.sendKeys("Qwerty, 123");
		signinPage.address2.sendKeys("zxcvb");
		signinPage.city.sendKeys("Qwerty");
		WebPageUtils.selectByVisibleText(signinPage.state, "Colorado");
		signinPage.postcode.sendKeys("12345");
		signinPage.other.sendKeys("Qwerty");
		signinPage.phone.sendKeys("12345123123");
		signinPage.phone_mobile.sendKeys("12345123123");
		signinPage.alias.sendKeys("hf");
		signinPage.submitAccount.click();
		WebPageUtils.explicitWaitByVisibilityOfElement(driver,signinPage.heading);
		//Assertions
		Assert.assertEquals(signinPage.heading.getText(), "MY ACCOUNT");
		Assert.assertEquals(signinPage.accountName.getText(), name + " " + surname);
		Assert.assertTrue(signinPage.infoAccountName.getText().contains("Welcome to your account."));
		Assert.assertTrue(signinPage.logout.isDisplayed());
		Assert.assertTrue(driver.getCurrentUrl().contains("controller=my-account"));
	}
	
	/**
	 * <pre>
	 * Login Existing Customer
	 * </pre>
	 * <p>
	 * <pre>
	 * Test:Login Existing Customer
	 *     Steps:
	 *     1. Initializing Login PageFactory calling login function
	 *     2. Used WebDriverUtility function
	 *     4. Validating Assertions    
	 */

	@Test(priority=2)
	public void logInTest() {

		logger.Info("LoginTest Starts ");

		//Calling Login Function
		login();
		String fullName = "Joe Black";

		WebPageUtils.explicitWaitByVisibilityOfElement(driver,signinPage.heading);

		//Assertions
		Assert.assertEquals("MY ACCOUNT", signinPage.heading.getText());
		Assert.assertEquals(fullName, signinPage.accountName.getText());
		Assert.assertTrue(signinPage.infoAccountName.getText().contains("Welcome to your account."));
		Assert.assertTrue(signinPage.logout.isDisplayed());
		Assert.assertTrue(driver.getCurrentUrl().contains("controller=my-account"));
	}
	
	/**
	 * <pre>
	 * Verify orderCheckout Details
	 * </pre>
	 * <p>
	 * <pre>
	 * Test:orderCheckout Details
	 *     Steps:
	 *     1. Initializing Checkout PageFactory calling login function
	 *     2. Setting up data on page using WebDriverUtility function
	 *     3. As Firefox/Chrome needs different handling for clicks so added condition
	 *     4. Selecting dressName from dataProvider an any number of test cases can be added. For now 1 test case added
	 *     5. Validating Assertions
	 *     
	 * </pre>
	 *</p>
	 * @param sTestId       TestCase ID
	 * @param sTestDesc     TestCase Description
	 * @throws Exception
	 */

	@Test(dataProvider = "orderCheckout", dataProviderClass = WebTestDataProvider.class, priority=3)
	public void checkoutTest(String testId, String dressName) {

		logger.Info("CheckoutTest Starts ");

		//Calling Login function
		login();

		//Selecting Women
		WebPageUtils.explicitWaitByVisibilityOfElement(driver,checkoutPage.gender);
		checkoutPage.gender.click();

		//Different Conditions because Firefox accepts only 1 click and Chrome 2 clicks (Needs Application Fix) - selecting DressName
		if(browserRun.equalsIgnoreCase("Firefox")) {
			driver.findElement(By.xpath("//a[@title='"+dressName+"']/ancestor::li")).click();
		}else {
			driver.findElement(By.xpath("//a[@title='"+dressName+"']/ancestor::li")).click();
			driver.findElement(By.xpath("//a[@title='"+dressName+"']/ancestor::li")).click();
		}
		
		WebPageUtils.explicitWaitByVisibilityOfElement(driver,checkoutPage.submitButton);
		checkoutPage.submitButton.click();		
		WebPageUtils.explicitWaitByVisibilityOfElement(driver,checkoutPage.checkOutPopUp);
		checkoutPage.checkOutPopUp.click();
		WebPageUtils.explicitWaitByVisibilityOfElement(driver,checkoutPage.checkOut);
		checkoutPage.checkOut.click();
		WebPageUtils.explicitWaitByVisibilityOfElement(driver,checkoutPage.addressProceed);
		checkoutPage.addressProceed.click();
		WebPageUtils.explicitWaitByVisibilityOfElement(driver,checkoutPage.terms);
		checkoutPage.terms.click();
		checkoutPage.shippingProceed.click();
		WebPageUtils.explicitWaitByVisibilityOfElement(driver,checkoutPage.bankwire);
		checkoutPage.bankwire.click();
		WebPageUtils.explicitWaitByVisibilityOfElement(driver,checkoutPage.payment);
		checkoutPage.payment.click();
		WebPageUtils.explicitWaitByVisibilityOfElement(driver,signinPage.heading);

		Assert.assertEquals("ORDER CONFIRMATION", signinPage.heading.getText());
		Assert.assertTrue(checkoutPage.shippingText.isDisplayed());
		Assert.assertTrue(checkoutPage.paymentText.isDisplayed());
		Assert.assertTrue(checkoutPage.orderConfirmText.getText().contains("Your order on My Store is complete."));
		Assert.assertTrue(driver.getCurrentUrl().contains("controller=order-confirmation"));
	}

	public void login() {
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login"))).click();
		loginPage.email.sendKeys(existingUserEmail);
		loginPage.password.sendKeys(existingUserPassword);
		loginPage.loginButton.click();
	}

	@AfterMethod
	public void tearDown(ITestResult result)
	{

		// Here will compare if test is failing then only it will enter into if condition and takes screenshot
		if(ITestResult.FAILURE==result.getStatus())
		{
			try 
			{

				TakesScreenshot ts=(TakesScreenshot)driver;
				File source=ts.getScreenshotAs(OutputType.FILE);

				// Copy files to specific location here it will save all screenshot in our project home directory and
				// result.getName() will return name of test case so that screenshot name will be same
				FileUtils.copyFile(source, new File("./Screenshots/"+result.getName()+".png"));

				System.out.println("Screenshot taken");
			} 
			catch (Exception e)
			{

				System.out.println("Exception while taking screenshot "+e.getMessage());
			} 
		}
		driver.quit();
	}
}

