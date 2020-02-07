package com.hellofresh.challenge;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * CheckoutPage PageFactory class
 */

public class CheckoutPage {
	WebDriver driver;
	
	@FindBy(how = How.LINK_TEXT, using = "Women")
	WebElement gender;
	@FindBy(how = How.NAME, using = "Submit")
	WebElement submitButton;
	@FindBy(how = How.XPATH, using = "//*[@id='layer_cart']//a[@class and @title='Proceed to checkout']")
	WebElement checkOutPopUp;
	@FindBy(how = How.XPATH, using = "//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']")
	WebElement checkOut;
	@FindBy(how = How.NAME, using = "processAddress")
	WebElement addressProceed;
	@FindBy(how = How.ID, using = "uniform-cgv")
	WebElement terms;
	@FindBy(how = How.NAME, using = "processCarrier")
	WebElement shippingProceed;
	@FindBy(how = How.CLASS_NAME, using = "bankwire")
	WebElement bankwire;
	@FindBy(how = How.XPATH, using = "//*[@id='cart_navigation']/button")
	WebElement payment;
	@FindBy(how = How.XPATH, using = "//li[@class='step_done step_done_last four']")
	WebElement shippingText;
	@FindBy(how = How.XPATH, using = "//li[@id='step_end' and @class='step_current last']")
	WebElement paymentText;
	@FindBy(how = How.XPATH, using = "//*[@class='cheque-indent']/strong")
	WebElement orderConfirmText;
	
	public CheckoutPage(WebDriver driver){
		this.driver = driver;
	}

}
